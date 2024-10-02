module es.ieslosmontecillos.prestamo {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.prestamo to javafx.fxml;
    exports es.ieslosmontecillos.prestamo;
}