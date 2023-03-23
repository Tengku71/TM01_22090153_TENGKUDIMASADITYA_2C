import Model.ResponseModel;
import network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends JFrame{
    private JButton buttonSubmit;
    private JButton buttonClose;
    private JTextField textFieldMsg;
    private JTextField textFieldStatus;
    private JTextField textFieldComment;
    private JLabel labelMsg;
    private JLabel labelStatus;
    private JLabel labelComment;
    private JPanel panelMain;
    private JButton buttonCLose;
    private JButton buttonMin;

    public MainActivity(){
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConnectURI koneksisaya = new ConnectURI();
                    URL myAddress = koneksisaya.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
                    String response = koneksisaya.getResponseFromHttpUrl(myAddress);
                    System.out.println(response);

                    JSONArray responseJSON = new JSONArray(response);
                    ArrayList<ResponseModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        ResponseModel resModel = new ResponseModel();
                        JSONObject myJSONObject = responseJSON.getJSONObject(i);
                        resModel.setMsg(myJSONObject.getString("message"));
                        resModel.setComment(myJSONObject.getString("status"));
                        resModel.setStatus(myJSONObject.getString("comment"));
                        responseModel.add(resModel);}
                    JOptionPane.showMessageDialog(null,"Data Sudah Didapat");
                    for (int index = 0; index < responseModel.size(); index++) {
                        textFieldMsg.setText(responseModel.get(index).getMsg());
                        textFieldStatus.setText(responseModel.get(index).getStatus());
                        textFieldComment.setText(responseModel.get(index).getComment());

                    }}catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        buttonCLose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldMsg.setText("");
                textFieldStatus.setText("");
                textFieldComment.setText("");
                JOptionPane.showMessageDialog(null,"Data sudah dibersihkan");
            }
        });
        buttonMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("MainActivity");
        frame.setContentPane(new MainActivity().panelMain);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
