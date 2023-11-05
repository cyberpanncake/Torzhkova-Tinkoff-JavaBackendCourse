package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.view.MazeViewer;
import edu.project2.view.PixelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MazeViewerTest {

    @ParameterizedTest
    @MethodSource("mazes")
    void mazeViewTest(Maze maze, String expected) {
        String actual = MazeViewer.getMazeView(maze);
        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] mazes() {
        return new Arguments[] {
            Arguments.of(new Maze(1, 1,
                Map.of(new Cell(0, 0), Collections.emptySet())),
                MazeViewer.render(new PixelType[][] {
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL },
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL },
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL },
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL }
                    })),
            Arguments.of(new Maze(3, 3,
                    Map.of( new Cell(0, 0), Set.of(new Cell(1, 0)),
                            new Cell(0, 1), Set.of(new Cell(0, 2)),
                            new Cell(0, 2), Set.of(new Cell(1, 2)),
                            new Cell(1, 0), Set.of(new Cell(0, 0), new Cell(2, 0)),
                            new Cell(1, 1), Set.of(new Cell(1, 2), new Cell(2, 1)),
                            new Cell(1, 2), Set.of(new Cell(0, 2), new Cell(1, 1)),
                            new Cell(2, 0), Set.of(new Cell(1, 0), new Cell(2, 1)),
                            new Cell(2, 1), Set.of(new Cell(1, 1), new Cell(2, 0), new Cell(2, 2)),
                            new Cell(2, 2), Set.of(new Cell(2, 1)))),
                MazeViewer.render(new PixelType[][] {
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL}
                }))
        };
    }

    @Test
    void mazeViewExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MazeViewer.getMazeView(null));
    }

    @ParameterizedTest
    @MethodSource("mazesWithPath")
    void mazeWithPathViewTest(Maze maze, List<Cell> path, String expected) {
        String actual = MazeViewer.getMazeView(maze, path);
        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] mazesWithPath() {
        return new Arguments[] {
            Arguments.of(new Maze(1, 1,
                    Map.of(new Cell(0, 0), Collections.emptySet())),
                List.of(new Cell(0, 0), new Cell(0, 0)),
                MazeViewer.render(new PixelType[][] {
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL },
                    { PixelType.WALL, PixelType.START_CELL, PixelType.END_CELL, PixelType.WALL },
                    { PixelType.WALL, PixelType.START_CELL, PixelType.END_CELL, PixelType.WALL },
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL }
                })),
            Arguments.of(new Maze(3, 3,
                    Map.of( new Cell(0, 0), Set.of(new Cell(1, 0)),
                            new Cell(0, 1), Set.of(new Cell(0, 2)),
                            new Cell(0, 2), Set.of(new Cell(1, 2)),
                            new Cell(1, 0), Set.of(new Cell(0, 0), new Cell(2, 0)),
                            new Cell(1, 1), Set.of(new Cell(1, 2), new Cell(2, 1)),
                            new Cell(1, 2), Set.of(new Cell(0, 2), new Cell(1, 1)),
                            new Cell(2, 0), Set.of(new Cell(1, 0), new Cell(2, 1)),
                            new Cell(2, 1), Set.of(new Cell(1, 1), new Cell(2, 0), new Cell(2, 2)),
                            new Cell(2, 2), Set.of(new Cell(2, 1)))),
                List.of(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0), new Cell(2, 1),
                        new Cell(1, 1), new Cell(1, 2), new Cell(0, 2), new Cell(0, 1)),
                MazeViewer.render(new PixelType[][] {
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL},
                    { PixelType.WALL, PixelType.START_CELL, PixelType.START_CELL, PixelType.WALL,
                        PixelType.END_CELL, PixelType.END_CELL, PixelType.PATH,
                        PixelType.PATH, PixelType.PATH, PixelType.WALL},
                    { PixelType.WALL, PixelType.START_CELL, PixelType.START_CELL, PixelType.WALL,
                        PixelType.END_CELL, PixelType.END_CELL, PixelType.PATH,
                        PixelType.PATH, PixelType.PATH, PixelType.WALL},
                    { PixelType.WALL, PixelType.PATH, PixelType.PATH, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.PATH, PixelType.PATH, PixelType.WALL},
                    { PixelType.WALL, PixelType.PATH, PixelType.PATH, PixelType.WALL,
                        PixelType.PATH, PixelType.PATH, PixelType.PATH,
                        PixelType.PATH, PixelType.PATH, PixelType.WALL},
                    { PixelType.WALL, PixelType.PATH, PixelType.PATH, PixelType.WALL,
                        PixelType.PATH, PixelType.PATH, PixelType.PATH,
                        PixelType.PATH, PixelType.PATH, PixelType.WALL},
                    { PixelType.WALL, PixelType.PATH, PixelType.PATH, PixelType.WALL,
                        PixelType.PATH, PixelType.PATH, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL},
                    { PixelType.WALL, PixelType.PATH, PixelType.PATH, PixelType.PATH,
                        PixelType.PATH, PixelType.PATH, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.PATH, PixelType.PATH, PixelType.PATH,
                        PixelType.PATH, PixelType.PATH, PixelType.FLOOR,
                        PixelType.FLOOR, PixelType.FLOOR, PixelType.WALL},
                    { PixelType.WALL, PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL,
                        PixelType.WALL, PixelType.WALL, PixelType.WALL}
                }))
        };
    }

    @Test
    void mazeWithPathViewExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> MazeViewer.getMazeView(null, Collections.emptyList()));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> MazeViewer.getMazeView(
                new Maze(1, 1, Map.of(new Cell(0, 0), Collections.emptySet())), null));
    }
}
