package chatapplication;

import static chatapplication.Client.doc;
import static chatapplication.Client.tf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

class Server extends JFrame{
    JPanel mainPanel,panel1;
    JButton sendbtn;
    static JTextField tf;
    static JTextPane tp;
    JLabel label;
    static Document doc;
    String serverName="Server";
    public void send(){
        try{
            SimpleAttributeSet attr1 = new SimpleAttributeSet();
            StyleConstants.setBold(attr1,true);
            
            StyleConstants.setBackground(attr1, new Color(218, 153, 170));
            Client.doc.insertString(Client.doc.getLength(),serverName+" : ",attr1);
            Client.doc.insertString(Client.doc.getLength(),tf.getText()+"\n",attr1);

            SimpleAttributeSet attr2 = new SimpleAttributeSet();
            StyleConstants.setBold(attr2,true);
            StyleConstants.setBackground(attr2,new Color(202,241,222));
            doc.insertString(doc.getLength(),serverName+" : ",attr2);
            doc.insertString(doc.getLength(),tf.getText()+"\n", attr2);
  
            tf.setText("");
        }catch(BadLocationException e1){}
    }
    Server(){
        setBounds(20,100,400,400);
        setTitle(serverName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        
        label = new JLabel(serverName);
        label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    String s = JOptionPane.showInputDialog(
                        null,
                        "Plz enter new name !",
                        "Input",
                        JOptionPane.QUESTION_MESSAGE
                    );
                    serverName = s;
                    label.setText(serverName);
                }
            }
        });
        label.setFont(new Font("Arial",Font.BOLD,20));
        tp = new JTextPane();
        tp.setEditable(false);
        tf = new JTextField(25);
        tf.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent e){
               if(e.getKeyCode()==10){
                   send();
               }
           }
        });
        sendbtn = new JButton("Send");
    
        doc = tp.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBold(attr,true);
        
        sendbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               send();
            }
        });
        panel1 = new JPanel();
        panel1.add(tf);
        panel1.add(sendbtn);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(label,BorderLayout.NORTH);
        mainPanel.add(tp,BorderLayout.CENTER);
        mainPanel.add(panel1,BorderLayout.SOUTH);

        add(mainPanel);
        
    }
}


class Client extends JFrame{
    JPanel mainPanel,panel1;
    JButton sendbtn;
    static JTextField tf;
    static JTextPane tp;
    JLabel label;
    static Document doc;
    String clientName="Client";
    public void send(){
        try{
            SimpleAttributeSet attr1 = new SimpleAttributeSet();
            StyleConstants.setBold(attr1,true);
            StyleConstants.setBackground(attr1, new Color(202,241,222));
            doc.insertString(doc.getLength(),clientName+" : ",attr1);
            doc.insertString(doc.getLength(),tf.getText()+"\n",attr1);

            SimpleAttributeSet attr2 = new SimpleAttributeSet();
            StyleConstants.setBold(attr2,true);
            StyleConstants.setBackground(attr2,new Color(218, 153, 170));
            Server.doc.insertString(Server.doc.getLength(),clientName+" : ", attr2);
            Server.doc.insertString(Server.doc.getLength(),tf.getText()+"\n", attr2);
            
            tf.setText("");
        }catch(BadLocationException e1){}
    }
    Client(){
        setBounds(500,100,400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        setTitle("Client");
        
        label = new JLabel(clientName);
        label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    String s = JOptionPane.showInputDialog(
                        null,
                        "Plz enter new name !",
                        "Input",
                        JOptionPane.QUESTION_MESSAGE
                    );
                    clientName = s;
                    label.setText(clientName);
                }
            }
        });
        label.setFont(new Font("Arial",Font.BOLD,20));
        tp = new JTextPane();
        tf = new JTextField(25);
        sendbtn = new JButton("Send");
    
        tp.setOpaque(true);
        tp.setEditable(false);
        doc = tp.getStyledDocument();
           
        sendbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                send();
            }
        });
        tf.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                System.out.println(e.getKeyCode());
                if(e.getKeyCode()==10){
                   send();
                }

            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        panel1 = new JPanel();
        panel1.add(tf);
        panel1.add(sendbtn);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(label,BorderLayout.NORTH);
        mainPanel.add(tp,BorderLayout.CENTER);
        mainPanel.add(panel1,BorderLayout.SOUTH);

        add(mainPanel);
        
    }
}



public class ChatAppplication {
    public static void main(String args[]){
        new Server().setVisible(true);
        new Client().setVisible(true);
    }
}
