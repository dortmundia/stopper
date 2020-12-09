import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;

public class Stopper extends JFrame{
    JPanel panel;
    JButton btnStart,btnReset;
    String startString="Start";
    String stopString = "Stop";
    String reszidoString = "Részidő";
    String resetString="Reset";
    JLabel lb1,lb2;
    JList<String> lista;
    ArrayList<String> aLista;
    DefaultListModel<String> model = new DefaultListModel<>();
    Timer timer;
    boolean isRunning=false;



    public Stopper()
    {
        init();
    }


    int szamlalo =0;


    ActionListener idozitoListener = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            szamlalo++;
            lb1.setText(formazas(szamlalo));
        }
    };

    private void setRunning(boolean running)
    {
        isRunning=running;
        if (running)
        {
            timer.start();
            btnStart.setText(stopString);
            btnReset.setText(reszidoString);
        }
        else
        {
            timer.stop();
            btnStart.setText(startString);
            btnReset.setText(resetString);
        }

    }

    //      private void reszidot()    {    }

    private String formazas(int ertek )
    {
        long millis = ertek;
        String hms = String.format("%02d:%02d:%02d.%03d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                TimeUnit.MILLISECONDS.toMillis(millis%1000)      );


        return hms;
    }



    private void init() {



        this.setTitle("Stopper");
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = (JPanel) (this.getContentPane());
        this.panel.setLayout(null);
        this.panel.setBackground(Color.white);
        btnStart = new JButton(startString);
        btnReset = new JButton(resetString);
        btnStart.setBounds(30,20,130,30);
        btnReset.setBounds(30,70,130,30);
        this.panel.add(btnReset);
        this.panel.add(btnStart);


        lb1=new JLabel();
        lb1.setText(formazas(szamlalo));
        lb1.setFont(new Font("Serif",Font.BOLD,40));
        lb1.setBounds(200,30,300,50);
        this.panel.add(lb1);

//push pls
        timer = new Timer(1, idozitoListener);



        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // timer.start();
                isRunning = !isRunning; // -> mindig vált
                setRunning(isRunning);
            }
        });


        lista= new JList<>(model);
        lista.setBounds(200,100,250,250);
        this.panel.add(lista);

        aLista=new ArrayList<>();


        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning)
                {
                    szamlalo =0;
                    lb1.setText(formazas(szamlalo));
                }
                else
                {
                    //aLista.add(Integer.toString(x));
                      model.addElement(formazas(szamlalo));
                }
            }
        });






        this.setVisible(true);
    }

}