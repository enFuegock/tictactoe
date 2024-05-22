package com.kodilla.tttfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tic Tac Toe Game Test Suite")
public class TicTacToeTestSuite {
    private MainBrain mainBrain;
    private GridPane gridPane;

    @BeforeAll
    public static void initJFX() throws InterruptedException {
        new Thread(() -> Application.launch(DummyApplication.class)).start();
        Thread.sleep(500);
    }

    @Nested
    @DisplayName("Tests for 3x3 size board for 'O' wins")
    class OwinsSmallBoard {

        @BeforeEach
        public void setUp() throws InterruptedException {
            mainBrain = new MainBrain() {
                @Override
                public void start(Stage primaryStage) {
                }
            };
            Platform.runLater(() -> {
                gridPane = new GridPane();
                mainBrain.createBoard(gridPane, 3);
            });
            Thread.sleep(500);
        }

        @Test
        public void testHorizontalWins() {
            for (int i = 0; i < 3; i++) {
                mainBrain.buttons[i][0].setText("O");
                mainBrain.buttons[i][1].setText("O");
                mainBrain.buttons[i][2].setText("O");
                assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
            }
        }

        @Test
        public void testVerticalWins() {
            for (int i = 0; i < 3; i++) {
                mainBrain.buttons[0][i].setText("O");
                mainBrain.buttons[1][i].setText("O");
                mainBrain.buttons[2][i].setText("O");
                assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
            }
        }

        @Test
        public void testDiagonalWin() {
            mainBrain.buttons[0][0].setText("O");
            mainBrain.buttons[1][1].setText("O");
            mainBrain.buttons[2][2].setText("O");
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));

        }

        @Test
        public void testDiagonalWin2() {
            mainBrain.buttons[0][2].setText("O");
            mainBrain.buttons[1][1].setText("O");
            mainBrain.buttons[2][0].setText("O");
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }
    }

    @Nested
    @DisplayName("Tests for 3x3 size board for 'X' wins")
    class XwinsSmallBoard {
        @BeforeEach
        public void setUp() throws InterruptedException {
            mainBrain = new MainBrain() {
                @Override
                public void start(Stage primaryStage) {
                }
            };
            Platform.runLater(() -> {
                gridPane = new GridPane();
                mainBrain.createBoard(gridPane, 3);
            });
            Thread.sleep(500);
        }

        @Test
        public void testHorizontalWins() {
            for (int i = 0; i < 3; i++) {
                mainBrain.buttons[i][0].setText("X");
                mainBrain.buttons[i][1].setText("X");
                mainBrain.buttons[i][2].setText("X");
                assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
            }
        }

        @Test
        public void testVerticalWins() {
            for (int i = 0; i < 3; i++) {
                mainBrain.buttons[0][i].setText("X");
                mainBrain.buttons[1][i].setText("X");
                mainBrain.buttons[2][i].setText("X");
                assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
            }
        }

        @Test
        public void testDiagonalWin() {
            mainBrain.buttons[0][0].setText("X");
            mainBrain.buttons[1][1].setText("X");
            mainBrain.buttons[2][2].setText("X");
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));

        }

        @Test
        public void testDiagonalWin2() {
            mainBrain.buttons[0][2].setText("X");
            mainBrain.buttons[1][1].setText("X");
            mainBrain.buttons[2][0].setText("X");
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }
    }

    @Nested
    @DisplayName("Tests for 10x10 size board for 'X' wins")
    class XwinsBigBoard {
        @BeforeEach
        public void setUp() throws InterruptedException {
            mainBrain = new MainBrain() {
                @Override
                public void start(Stage primaryStage) {
                }
            };
            Platform.runLater(() -> {
                gridPane = new GridPane();
                mainBrain.createBoard(gridPane, 10);
            });
            Thread.sleep(500);
        }

        @Test
        public void testHorizontalWins() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[0][i].setText("X");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }

        @Test
        public void testVerticalWins() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[i][0].setText("X");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }

        @Test
        public void testDiagonalWins() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[i][i].setText("X");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }

        @Test
        public void testDiagonalWins2() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[i][4 - i].setText("X");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }
    }

    @Nested
    @DisplayName("Tests for 10x10 size board for 'O' wins")
    class OwinsBigBoard {
        @BeforeEach
        public void setUp() throws InterruptedException {
            mainBrain = new MainBrain() {
                @Override
                public void start(Stage primaryStage) {
                }
            };
            Platform.runLater(() -> {
                gridPane = new GridPane();
                mainBrain.createBoard(gridPane, 10);
            });
            Thread.sleep(500);
        }

        @Test
        public void testHorizontalWins() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[0][i].setText("O");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }

        @Test
        public void testVerticalWins() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[i][0].setText("O");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }

        @Test
        public void testDiagonalWins() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[i][i].setText("O");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }

        @Test
        public void testDiagonalWins2() {
            for (int i = 0; i < 5; i++) {
                mainBrain.buttons[i][4 - i].setText("O");
            }
            assertTrue(mainBrain.checkWin(mainBrain.buttons.length));
        }
    }

    @Nested
    @DisplayName("Miscellaneous tests")
    class MiscellaneousTestsSmallAndBig {
        @BeforeEach
        public void setUp() throws InterruptedException {
            mainBrain = new MainBrain() {
                @Override
                public void start(Stage primaryStage) {
                }
            };
            Platform.runLater(() -> {
                gridPane = new GridPane();
                mainBrain.createBoard(gridPane, 3);
            });
            Thread.sleep(500);
        }

        @Test
        public void testDraw() {
            mainBrain.buttons[0][0].setText("X");
            mainBrain.buttons[0][1].setText("O");
            mainBrain.buttons[0][2].setText("X");
            mainBrain.buttons[1][0].setText("X");
            mainBrain.buttons[1][1].setText("O");
            mainBrain.buttons[1][2].setText("O");
            mainBrain.buttons[2][0].setText("O");
            mainBrain.buttons[2][1].setText("X");
            mainBrain.buttons[2][2].setText("X");
            assertTrue(mainBrain.isDraw());

        }


    }
}

/*@Test
        public void testFalseMove() {
            game.getboard()[0][0] = 'O';
            game.getboard()[0][1] = 'X';
            assertFalse(game.isMoveCorrect(0, 1));
        }

 */

