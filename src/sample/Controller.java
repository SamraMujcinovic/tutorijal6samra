package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller  {

    public TextField tfIme;
    public TextField tfPrezime;
    public TextField tfIndex;
    public TextField tfJMBG;
    public DatePicker datePicker;
    public TextField tfAdresa;
    public TextField tfTelefon;
    public TextField tfMail;
    public ComboBox<String> comboMjestoRodj;
    public ChoiceBox<String> cboxOdsjek = new ChoiceBox<>();
    public ChoiceBox<String> cboxCiklus;
    public ChoiceBox<String> cboxGodina;
    public RadioButton rbRedovan;
    public RadioButton rbSamofinansirajuci;
    public CheckBox cboxBoracka;
    public Button btnPotvrdi;
    private Boolean imeIspravno;
    private Boolean prezimeIspravno;
    private Boolean indexIspravno;
    private Boolean JMBGIspravno;
    private Boolean mailIspravno;
    public String s,s1, s2, formattedString;
    private Pattern patern;
    private Matcher matcher;
    public LocalDate d;


    public Boolean imeValidiraj(String n){
        if (n.length() < 1 || n.length() > 20) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'z'))
                return false;
        }
        return !n.trim().isEmpty();
    }

    public Boolean prezimeValidiraj(String prezime){
        if(prezime.trim().isEmpty()) return false;
        Boolean validno = true;
        if(prezime.length() > 20 || prezime.length() < 1) validno = false;
        else{
            for(int i=0;i<prezime.length();i++){
                if(!((prezime.charAt(i) >= 'a' && prezime.charAt(i)<= 'z') || (prezime.charAt(i) >= 'A' && prezime.charAt(i) <= 'Z'))) validno = false;
            }
        }
        return validno;
    }

    public Boolean indexValidiraj(String index){
        if(index.trim().isEmpty()) return false;
        if(index.length() > 5 ) return false;
        for(int i=0; i< index.length();i++){
            if(!(index.charAt(i) >= '0' && index.charAt(i) <= '9')) return false;
        }
        return true;
    }

    public Boolean JMBGValidiraj(String jmbg){
        if(jmbg.trim().isEmpty()) return false;
        if(jmbg.length() != 13 ) return false;
        for(int i=0;i<jmbg.length();i++) if(jmbg.charAt(i) > '9' || jmbg.charAt(i) < '0') return false;
        /*LocalDate datum = datePicker.getValue();
        int dan = 0, mjesec = 0, godina = 1900;
        if(datum.getYear() < 1900) return false;
        String datIzJMBG = jmbg;
        dan = datIzJMBG.charAt(0) * 10 + datIzJMBG.charAt(1);
        mjesec = datIzJMBG.charAt(2) *10 + datIzJMBG.charAt(3);
        godina += datIzJMBG.charAt(5)*10+datIzJMBG.charAt(6);
        if(!(datum.getDayOfMonth() == dan && datum.getMonthValue() == mjesec && datum.getYear() == godina)) return false;*/
        return true;
    }

    public Boolean formularIspravan(){
        return (imeIspravno && prezimeIspravno && indexIspravno && mailIspravno && JMBGIspravno );
    }

    public Boolean eMailValidiraj(String mail){
        if(mail.trim().isEmpty()) return false;
        patern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        matcher = patern.matcher(mail);
        return matcher.matches();

    }

    @FXML
    public void initialize() {
        imeIspravno = false;
        tfIme.getStyleClass().add("poljeNeispravno");

        tfIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(imeValidiraj(newValue)){
                    tfIme.getStyleClass().removeAll("poljeNeispravno");
                    tfIme.getStyleClass().add("poljeIspravno");
                    imeIspravno = true;
                }
                else{
                    tfIme.getStyleClass().removeAll("poljeIspravno");
                    tfIme.getStyleClass().add("poljeNeispravno");
                    imeIspravno = false;
                }
            }
        });

        tfPrezime.getStyleClass().add("poljeNeispravno");
        prezimeIspravno = false;
        tfPrezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(prezimeValidiraj(newValue)){
                    tfPrezime.getStyleClass().removeAll("poljeNeispravno");
                    tfPrezime.getStyleClass().add("poljeIspravno");
                    prezimeIspravno = true;
                }
                else{
                    tfPrezime.getStyleClass().removeAll("poljeIspravno");
                    tfPrezime.getStyleClass().add("poljeNeispravno");
                    prezimeIspravno = false;
                }
            }
        });

        tfIndex.getStyleClass().add("poljeNeispravno");
        indexIspravno = false;
        tfIndex.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(indexValidiraj(newValue)){
                    tfIndex.getStyleClass().removeAll("poljeNeispravno");
                    tfIndex.getStyleClass().add("poljeIspravno");
                    indexIspravno = true;
                }
                else{
                    tfIndex.getStyleClass().removeAll("poljeIspravno");
                    tfIndex.getStyleClass().add("poljeNeispravno");
                    indexIspravno = false;
                }
            }
        });

        tfJMBG.getStyleClass().add("poljeNeispravno");
        JMBGIspravno = false;
        tfJMBG.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(JMBGValidiraj(newValue)){
                    tfJMBG.getStyleClass().removeAll("poljeNeispravno");
                    tfJMBG.getStyleClass().add("poljeIspravno");
                    JMBGIspravno = true;
                }
                else{
                    tfJMBG.getStyleClass().removeAll("poljeIspravno");
                    tfJMBG.getStyleClass().add("poljeNeispravno");
                    JMBGIspravno = false;
                }
            }
        });

        tfMail.getStyleClass().add("poljeNeispravno");
        mailIspravno = false;
        tfMail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (eMailValidiraj(newValue)) {

                    tfMail.getStyleClass().removeAll("poljeNeispravno");
                    tfMail.getStyleClass().add("poljeIspravno");
                    mailIspravno = true;
                }
                else{
                    tfMail.getStyleClass().removeAll("poljeIspravno");
                    tfMail.getStyleClass().add("poljeNeispravno");
                    mailIspravno = false;
                }
            }
        });


        String[] items1 = new String[]{"RI", "AiE", "TK", "EEK"};
        cboxOdsjek.setItems(FXCollections.observableArrayList(items1));
        cboxOdsjek.setValue("RI");

        String[] items2 = new String[]{"I", "II", "III"};
        cboxGodina.setItems(FXCollections.observableArrayList(items2));
        cboxGodina.setValue("");

        String[] items3 = new String[]{"I", "II", "III"};
        cboxCiklus.setItems(FXCollections.observableArrayList(items3));
        cboxCiklus.setValue("");

        comboMjestoRodj.getItems().addAll("Sarajevo", "Mostar", "Tuzla", "Gorazde", "Banja Luka", "Bihac");


        ToggleGroup grupa = new ToggleGroup();
        rbRedovan.setToggleGroup(grupa);
        rbSamofinansirajuci.setToggleGroup(grupa);


    }


    public void formularPotvrdi(ActionEvent actionEvent) {
        String ime = tfIme.getText();
        String prezime = tfPrezime.getText();
        String datum = datePicker.getValue().toString();
        String mjesto = comboMjestoRodj.getEditor().getText();
        String jmbg = tfJMBG.getText();
        String index = tfIndex.getText();
        String adresa = tfAdresa.getText();
        String telefon = tfTelefon.getText();
        String mail = tfMail.getText();



        if(rbRedovan.isSelected()) s = "redovan ";
        else if(rbSamofinansirajuci.isSelected()) s = "redovan samofinansirajuci ";
        if(cboxBoracka.isSelected()) s1 = "Pripada borackim kategorijama";
        else s1 = "Ne pripada borackim kategorijama";

        if(formularIspravan()) {
            System.out.println("Student: " + ime + " " + prezime + " (" + index + ")");
            System.out.println("JMBG: " + jmbg);
            System.out.println("Datum rodjenja: " + datum + ", mjesto rodjenja: " + mjesto);
            System.out.println("Ulica stanovanja: " + adresa);
            System.out.println("Broj telefona: " + telefon);
            System.out.println("Email adresa: " + mail);
            System.out.println("Upisan kao: " + s + " student, smjer: " + cboxOdsjek.getValue() + ", godina: " + cboxGodina.getValue() + ", ciklus: " + cboxCiklus.getValue());
            System.out.println(s1);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije validno!");
            alert.setHeaderText("Uneseni podaci nisu validni ili polje nije popunjeno!");
            alert.setContentText("Molimo vas unesite ponovo vase podatke");
            alert.show();
        }
    }
}
