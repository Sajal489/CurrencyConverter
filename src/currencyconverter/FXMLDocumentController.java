/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currencyconverter;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author aryal
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TextField currentCurrencyField, changecurrencyField;
    @FXML
    ComboBox<String> startCur, endCur;
    @FXML
    Button convertButton;
    @FXML
    Label emptyerror, numbererror, selecterror1, selecterror2;
    List<Currency> curlist = new ArrayList<>();

    @FXML
    private void handleConvertAction(ActionEvent event) {
        emptyerror.setVisible(false);
        numbererror.setVisible(false);
        selecterror1.setVisible(false);
        selecterror2.setVisible(false);
        if (startCur.getValue() == null) {
            selecterror1.setVisible(true);
            return;
        }
        if (endCur.getValue() == null) {
            selecterror2.setVisible(true);
            return;
        }
        if (currentCurrencyField.getText().equals("")) {
            emptyerror.setVisible(true);
            return;
        }
        double curval = -1;
        try {
            curval = Double.parseDouble(currentCurrencyField.getText());
        } catch (NumberFormatException e) {
            numbererror.setVisible(true);
            return;
        }
        double rate = -1;
        for (int i = 0; i < curlist.size(); i++) {
            if (curlist.get(i).country.equals(startCur.getValue().substring(0, 3))) {
                rate = curlist.get(i).fromcountry;
            }
        }
        double rate2 = -1;
        for (int i = 0; i < curlist.size(); i++) {
            if (curlist.get(i).country.equals(endCur.getValue().substring(0, 3))) {
                rate2 = curlist.get(i).tocountry;
            }
        }
        double finalval = (curval * rate) * rate2;
        changecurrencyField.setText(Double.toString(finalval));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("D:\\My_Programs\\Java\\ExtraSpace\\CurrencyConverter\\src\\sd.txt");
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Scanner reader;
            reader = new Scanner(file);
            while (reader.hasNext()) {
                String cur = reader.nextLine();
                StringTokenizer tk = new StringTokenizer(cur);
                int tokenlength = tk.countTokens();
                Currency c = new Currency();
                c.country = tk.nextToken();
                String country = "";
                while (tk.countTokens() > 2) {
                    country += tk.nextToken() + " ";
                }
                c.currency = country;
                c.tocountry = Double.parseDouble(tk.nextToken());
                c.fromcountry = Double.parseDouble(tk.nextToken());
                list.add(c.country + " ( " + c.currency + ")");
                curlist.add(c);
            }
        } catch (FileNotFoundException ex) {
        }
        Collections.sort(list);
        startCur.setItems(list);
        endCur.setItems(list);
    }

}
