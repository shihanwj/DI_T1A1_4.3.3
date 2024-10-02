/**
 * 2º DAM DI
 * Autora: Shihan Wei
 * 4.3.3. Expresiones Lambda. ActionEvent
 */


package es.ieslosmontecillos.prestamo;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    final int MES = 12;

    @Override
    public void start(Stage PrimaryStage) {

        /* Creamos el pane donde vamos a colocar todos los elementos */
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

        /* creamos las etiquetas y campos de textos correspondientes */
        Label lbInteres = new Label("Annual Interest Rate: ");
        Label lbAnios = new Label("Number of Years: ");
        Label lbCant = new Label("Loan Amount: ");
        Label lbMensual = new Label("Monthly Payment: ");
        Label lbTotal = new Label("Total Payment: ");
        Label lbError = new Label();
        lbError.setTextFill(Paint.valueOf("red"));

        TextField tfInteres = new TextField();
        TextField tfAnios = new TextField();
        TextField tfCant = new TextField();
        TextField tfMensual = new TextField();
        TextField tfTotal = new TextField();
        /*Preguntar
        m.setDisable(true);
        t.setDisable(true);
         */

        /* Creamos el botón y el evento correspondiente cuando se pulsa el boton */
        Button calcular = new Button("Calculate");
        calcular.setOnAction(e ->{
            double r;
            double interestRate;
            double hipoteca;
            int anio;
            double cuotaMes;
            double aPagar;

            /* en el caso de que esté los campos vacíos */
            if(tfInteres.getText().isEmpty() || tfCant.getText().isEmpty() || tfAnios.getText().isEmpty())
            {
                lbError.setText("Error! Campo vacío");
            }else
            {
                try
                {
                    /* tomamos los valores de los textfields */
                    interestRate = Double.parseDouble(tfInteres.getText());
                    hipoteca = Double.parseDouble(tfCant.getText());
                    anio = Integer.parseInt(tfAnios.getText());

                    /* calculamos la cuota mensual a pagar según la información y el total a pagar*/
                    r = interestRate/(100*MES);
                    cuotaMes = (hipoteca*r)/(1-Math.pow((1+r),(-MES*anio)));
                    aPagar = anio*cuotaMes*MES;

                    /* Redondeamos los resultados a dos decimales*/
                    cuotaMes = Math.round(cuotaMes*100) / 100.00;
                    aPagar = Math.round(aPagar*100) / 100.00;

                    tfMensual.setText("€ " + cuotaMes);
                    tfTotal.setText("€ " + aPagar);

                }catch(NumberFormatException numberError)
                {
                    /* en el caso de que se introduzca un formato no válido */
                    lbError.setText("Error! Campo invalido");
                }

            }

        });

        /* Añadimos los elementos al GridPane */
        root.add(lbInteres, 0, 0);
        root.add(tfInteres, 1, 0);
        root.add(lbAnios, 0, 1);
        root.add(tfAnios, 1, 1);
        root.add(lbCant, 0, 2);
        root.add(tfCant, 1, 2);
        root.add(lbMensual, 0, 3);
        root.add(tfMensual, 1, 3);
        root.add(lbTotal, 0, 4);
        root.add(tfTotal, 1, 4);
        root.add(calcular, 1, 5);
        root.add(lbError, 0, 5);
        GridPane.setHalignment(calcular, HPos.RIGHT);

        /* Posicionamos el contenedor */
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(350, 250);

        Scene scene = new Scene(root);
        PrimaryStage.setTitle("Calculo de hipoteca");
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}