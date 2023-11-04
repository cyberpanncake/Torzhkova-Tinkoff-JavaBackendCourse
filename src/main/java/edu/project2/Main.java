package edu.project2;

import edu.project2.exception.NeedToStopException;
import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.generation.BinaryTreeMazeGenerator;
import edu.project2.maze.generation.DFSMazeGenerator;
import edu.project2.maze.generation.IMazeGenerator;
import edu.project2.maze.solution.BFSMazeSolver;
import edu.project2.maze.solution.DFSMazeSolver;
import edu.project2.maze.solution.IMazeSolver;
import edu.project2.view.MazeViewer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String WRONG_INPUT = "Неверный ввод, повторите попытку";
    private static final String WRONG_NUMBER = "Неверный номер алгоритма";
    private static IMazeGenerator generator;
    private static IMazeSolver solver;
    private static Maze maze;
    private static List<Cell> solution;

    private Main() {
    }

    public static void main(String[] args) {
        LOGGER.info("Добро пожаловать в программу \"Лабиринты\". Для завершения работы нажмите Ctrl + D");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                setGenerator(reader);
                generateMaze(reader);
                LOGGER.info("Сгенерированный лабиринт:" + MazeViewer.getMazeView(maze));
                processSolutions(reader);
                LOGGER.info("Сгенерировать новый лабиринт? (да/нет)");
                String input = null;
                try {
                    input = getInput(reader);
                } catch (Exception ex) {
                    LOGGER.warn(WRONG_INPUT);
                }
                if (!"да".equalsIgnoreCase(input)) {
                    throw new NeedToStopException();
                }
            }
        } catch (IOException e) {
            LOGGER.error("Произошла непредвиденная ошибка ввода-вывода. Работа завершена");
        } catch (NeedToStopException e) {
            LOGGER.info("Работа завершена");
        }
    }

    private static void setGenerator(BufferedReader reader) throws NeedToStopException {
        LOGGER.info("""
            Выберите алгоритм генерации лабиринта (введите номер алгоритма):
            1 - выращивание дерева с помощью обхода в глубину,
            2 - генерация на основе двоичных деревьев""");
        int algorithm = 0;
        while (algorithm == 0) {
            try {
                algorithm = Integer.parseInt(getInput(reader));
                if (algorithm < 1 || algorithm > 2) {
                    algorithm = 0;
                    throw new Exception();
                }
            } catch (Exception e) {
                LOGGER.warn(WRONG_INPUT);
            }
        }
        generator = switch (algorithm) {
            case 1 -> new DFSMazeGenerator();
            case 2 -> new BinaryTreeMazeGenerator();
            default -> throw new IllegalStateException(WRONG_NUMBER);
        };
    }

    private static void generateMaze(BufferedReader reader) throws NeedToStopException {
        maze = null;
        LOGGER.info("Введите размеры лабиринта (2 цифры через пробел, размер не больше, чем 20x50):");
        while (maze == null) {
            try {
                String[] input = getInput(reader).split(" ");
                if (input.length != 2) {
                    throw new Exception();
                }
                int n = Integer.parseInt(input[1]);
                int m = Integer.parseInt(input[0]);
                maze = generator.generate(n, m);
            } catch (Exception e) {
                LOGGER.warn(WRONG_INPUT);
            }
        }
    }

    private static void processSolutions(BufferedReader reader) throws NeedToStopException {
        while (true) {
            LOGGER.info("Найти решение в этом лабиринте? (да/нет)");
            String input = null;
            try {
                input = getInput(reader);
            } catch (Exception ex) {
                LOGGER.warn(WRONG_INPUT);
            }
            if (!"да".equalsIgnoreCase(input)) {
                break;
            }
            setSolver(reader);
            findSolution(reader);
            if (solution == null) {
                LOGGER.warn("Для данных координат решения не существует");
            } else {
                LOGGER.info("Найденное решение:" + MazeViewer.getMazeView(maze, solution));
            }
        }
    }

    private static void setSolver(BufferedReader reader) throws NeedToStopException {
        LOGGER.info("""
            Выберите алгоритм решения лабиринта (введите номер алгоритма):
            1 - поиск в глубину,
            2 - поиск в ширину""");
        int algorithm = 0;
        while (algorithm == 0) {
            try {
                algorithm = Integer.parseInt(getInput(reader));
                if (algorithm < 1 || algorithm > 2) {
                    algorithm = 0;
                    throw new Exception();
                }
            } catch (Exception e) {
                LOGGER.warn(WRONG_INPUT);
            }
        }
        solver = switch (algorithm) {
            case 1 -> new DFSMazeSolver();
            case 2 -> new BFSMazeSolver();
            default -> throw new IllegalStateException(WRONG_NUMBER);
        };
    }

    @SuppressWarnings("MagicNumber")
    private static void findSolution(BufferedReader reader) throws NeedToStopException {
        solution = null;
        LOGGER.info("Введите координаты начальной и конечной клеток\n"
            + "(4 цифры через пробел в формате \"x1 y1 x2 y2\", где 1 - начальная клетка, 2 - конечная):");
        while (solution == null) {
            try {
                String[] input = getInput(reader).split(" ");
                if (input.length != 4) {
                    throw new Exception();
                }
                Cell start = new Cell(Integer.parseInt(input[1]) - 1, Integer.parseInt(input[0]) - 1);
                Cell end = new Cell(Integer.parseInt(input[3]) - 1, Integer.parseInt(input[2]) - 1);
                solution = solver.solve(maze, start, end);
            } catch (Exception e) {
                LOGGER.warn(WRONG_INPUT);
            }
        }
    }

    private static String getInput(BufferedReader reader) throws NeedToStopException, IOException {
        String input = reader.readLine();
        if (input == null) {
            throw new NeedToStopException();
        }
        return input.trim();
    }
}
