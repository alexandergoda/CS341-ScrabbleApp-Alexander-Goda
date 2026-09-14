package scrabble;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Graphical application that accepts up to seven Scrabble tile letters and
 * displays all unique arrangements that use every entered tile.
 *
 * @author Alexander Goda
 */
public class ScrabbleApp {

    /** Main application window. */
    private JFrame frmScrabbleApp;

    /** Field used to enter Scrabble tile letters. */
    private JTextField txtTiles;

    /** Button used to generate the arrangements. */
    private JButton btnBuild;

    /** Area used to display arrangements or an input error. */
    private JTextArea txtOutput;

    /**
     * Launches the application.
     *
     * @param args command-line arguments are not used
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScrabbleApp window = new ScrabbleApp();
                    window.frmScrabbleApp.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Creates the application and registers its events.
     */
    public ScrabbleApp() {
        initialize();
        createEvents();
    }

    /**
     * Initializes the contents of the frame.
     */
    private void initialize() {
        frmScrabbleApp = new JFrame();
        frmScrabbleApp.setTitle("Scrabble App");
        frmScrabbleApp.setBounds(100, 100, 700, 600);
        frmScrabbleApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmScrabbleApp.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("SCRABBLE TILE ARRANGEMENTS");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        titleLabel.setBounds(120, 30, 460, 30);
        frmScrabbleApp.getContentPane().add(titleLabel);

        JLabel instructionLabel = new JLabel("Enter up to 7 Scrabble tile letters:");
        instructionLabel.setBounds(70, 95, 260, 25);
        frmScrabbleApp.getContentPane().add(instructionLabel);

        txtTiles = new JTextField();
        txtTiles.setBounds(330, 92, 290, 30);
        frmScrabbleApp.getContentPane().add(txtTiles);
        txtTiles.setColumns(10);

        btnBuild = new JButton("BUILD OUTPUT");
        btnBuild.setBounds(260, 145, 180, 35);
        frmScrabbleApp.getContentPane().add(btnBuild);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(70, 210, 550, 300);
        frmScrabbleApp.getContentPane().add(scrollPane);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setLineWrap(false);
        scrollPane.setViewportView(txtOutput);
    }

    /**
     * Registers the event listener for the build button.
     */
    private void createEvents() {
        btnBuild.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buildOutput();
            }
        });
    }

    /**
     * Reads the tile input, generates the arrangements, and displays the output.
     */
    private void buildOutput() {
        try {
            // Task 1: Read and validate the entered Scrabble tiles.
            ScrabbleGenerator generator = new ScrabbleGenerator(txtTiles.getText());

            // Task 2: Generate all arrangements using the entered tiles.
            List<String> arrangements = generator.generateArrangements();

            // Task 3: Build and display the output.
            txtOutput.setText(formatOutput(arrangements));
            txtOutput.setCaretPosition(0);

        } catch (IllegalArgumentException e) {
            txtOutput.setText("input error");
        }
    }

    /**
     * Formats the arrangements so that each one appears on its own line.
     *
     * @param arrangements arrangements to display
     * @return formatted output text
     */
    private String formatOutput(List<String> arrangements) {
        StringBuilder output = new StringBuilder();

        output.append("Total arrangements: ");
        output.append(arrangements.size());
        output.append("\n\n");

        for (String arrangement : arrangements) {
            output.append(arrangement);
            output.append("\n");
        }

        return output.toString();
    }
}
