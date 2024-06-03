module org.iesalandalus.programacion.reservashotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires javafx.graphics;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires java.logging;
    requires java.xml;
    //requires entrada;
    //requires entrada;
    //requires entrada;


    opens org.iesalandalus.programacion.reservashotel to javafx.fxml;
    exports org.iesalandalus.programacion.reservashotel;

    opens org.iesalandalus.programacion.reservashotel.vista to javafx.graphics;
    exports org.iesalandalus.programacion.reservashotel.vista.grafica;

    opens org.iesalandalus.programacion.reservashotel.vista.grafica.controladores to javafx.fxml;
    exports org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

    opens org.iesalandalus.programacion.reservashotel.modelo.dominio to javafx.base;
    exports org.iesalandalus.programacion.reservashotel.modelo.dominio;

}