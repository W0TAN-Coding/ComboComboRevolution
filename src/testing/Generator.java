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
        String frameLength = "float frameLength = 1000.0/60.0;\n\n";
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
        float delay = 0;
        for(Move move : combo.getMoves()) {
            if(lastMove != null) {
                if(!move.getInput().contains(Keys.DOWN)) {
                    sb.append("\tKeyboard.release(DOWN);\n");
                }
                delay = Math.max(1f, lastMove.getStartup() - lastMove.getHeldFrames() + lastMove.getActive() + lastMove.getHitstun());
                sb.append("\tdelay(").append(delay/2f).append(" * frameLength);\n");
            }

            // PRESS DOWN
            for(Keys key : move.getInput()) {
                sb.append("\tKeyboard.press(").append(key.name()).append(");\n");
            }

            // HOLD KEY
            sb.append("\tdelay(").append(move.getHeldFrames()).append(" * frameLength);\n");

            // RELEASE
            for(Keys key : move.getInput()) {
                if(!key.name().equals(Keys.DOWN.name())) {
                    sb.append("\tKeyboard.release(").append(key.name()).append(");\n");
                }
            }

            lastMove = move;
        }

        sb.append("}");
        fw.append(sb.toString());
    }
}
