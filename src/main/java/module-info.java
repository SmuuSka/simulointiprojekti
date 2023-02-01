module com.metropolia.simuryhmaYksi.sorttiasema {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.metropolia.simuryhmaYksi.sorttiasema to javafx.fxml;
    exports com.metropolia.simuryhmaYksi.sorttiasema;
}