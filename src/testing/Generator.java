package testing;

import game.character.Combo;
import game.character.Move;
import game.config.Keys;

import java.io.FileWriter;
import java.io.IOException;

public class Generator {
    private FileWriter fw;

    public Generator(String fileName) {
        try {
            fw = new FileWriter("src/resources/generator/combos/" + fileName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(Combo combo) throws IOException {
        createHeader();
        createCombo(combo);
        fw.close();
    }

    private void createHeader() throws IOException {
        String include = "#include <Keyboard.h>\n\n";
        String risingEdge = "boolean risingEdge;\n";
        String frameLength = "long frameLength = 1000/60;\n\n";
        StringBuilder asciiBuilder = new StringBuilder();
        for(Keys k : Keys.getAllKeybinds()) {
            asciiBuilder.append("int ").append(k.name()).append(" = ").append(k.getASCII()).append(";\n");
        }
        String asciiCodes = asciiBuilder.toString();
        String setup = "\nvoid setup() {\n\tpinMode(1, INPUT_PULLUP);\n\tKeyboard.begin();\n\trisingEdge = false;\n}\n\n";
        String loop = "void loop() {\n\tif(!digitalRead(1) && !risingEdge) {\n\t\trisingEdge = true;\n\t\tcombo();\n\t" +
                "} else if(digitalRead(1)){\n\t\trisingEdge = false;\n\t}\n}\n\n";

        fw.append(include);
        fw.append(risingEdge);
        fw.append(frameLength);
        fw.append(asciiCodes);
        fw.append(setup);
        fw.append(loop);
    }

    private void createCombo(Combo combo) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("void combo() {\n");
        Move lastMove = null;
        for(Move move : combo.getMoves()) {
            for(Keys key : move.getInput()) {
                sb.append("\tKeyboard.press(").append(key.name()).append(");\n");
            }
            sb.append("\tdelay(").append(Math.max(1, (1/2f) * move.getStartup())).append("L * frameLength + 4);\n");
            sb.append("\tKeyboard.releaseAll();\n");

            sb.append("\tdelay(").append(Math.max(1, (1/2f) * move.getStartup()) + move.getHitstop()).append("L * frameLength + 4);\n");

            lastMove = move;
        }

        sb.append("}");
        fw.append(sb.toString());
    }

    private void createComboOld(Combo combo) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("void combo() {\n");
        Move lastMove = null;
        for(Move move : combo.getMoves()) {
            sb.append("\tdelay(").append(lastMove == null || move.getInput().contains(Keys.UP) || move.getInput().contains(Keys.E)
                    ? move.getStartup() : move.getStartup() + lastMove.getHitstop()/2 ).append("L * frameLength);\n");
            for(Keys key : move.getInput()) {
                sb.append("\tKeyboard.press(").append(key.name()).append(");\n");
            }

            //sb.append("\tdelay(1L * frameLength + 5);\n");
            sb.append("\tdelay(").append(lastMove == null || move.getInput().contains(Keys.UP) || move.getInput().contains(Keys.E)
                    ? 2 : 1 + lastMove.getHitstop()/2).append("L * frameLength);\n");
            sb.append("\tKeyboard.releaseAll();\n");

            lastMove = move;
        }

        sb.append("}");
        fw.append(sb.toString());
    }
}
