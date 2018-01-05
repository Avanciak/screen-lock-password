import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class PasswdChecker {
    
    private String password;
    //private JLabel output;
    
    public PasswdChecker() {
        initialize();
    }
    public String readPasswordFromFile(String filename) {
        String password = "";
        File file = new File(filename);
        try{
                Scanner content = new Scanner(file);
                while(content.hasNext()){
                    password = content.nextLine();
                }
        content.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
                
        return password;
    }
    public void setPassword() {
       this.password = this.readPasswordFromFile("haslo.txt");
   }
    
    public void initialize() {
        JFrame window = new JFrame();
        //window.setBounds(100, 100, 300, 300);
        /*
        GraphicsEnvironment myEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice myDevice = myEnvironment.getDefaultScreenDevice();
        myDevice.setFullScreenWindow(window);
        */                         
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds(0, 0, screenSize.width, screenSize.height);
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setOpacity(0.5f);
        window.setTitle("Sprawdza hasło");
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        window.getContentPane().setLayout(null);
        window.setVisible(true);
                        
        JPasswordField input = new JPasswordField();
        
        input.setBounds(10, 30, 100, 20);
        input.setVisible(true);
        window.add(input);
        
        JLabel output = new JLabel();
        output.setText("Wprowadź hasło:");
        output.setBounds(5, 5, 190, 20);
        output.setFont(new Font("DIALOG",0,20));
        output.setVisible(true);
        window.add(output);
        
        Graphics g = window.getGraphics();
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    setPassword();
                    if(String.valueOf(input.getPassword()).equals(password)) {
                        output.setText("Hasło prawidłowe!");
                        output.setForeground(new Color(50,130,0));
                        window.paintAll(g);
                                               
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        System.exit(0);
                        //window.dispose();
                        
                        
                    }
                    else
                       
                        output.setText("Brak dostepu!!!");
                        output.setForeground(new Color(150,30,0));
                        window.paintAll(g);
                }
            }
        });
        
        
        window.repaint();
                           
        }

    public static void main(String[] args) {
        
        PasswdChecker program = new PasswdChecker();
              
                
    }

}
