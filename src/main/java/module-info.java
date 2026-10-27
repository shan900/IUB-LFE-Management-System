
module org.example.iub_lfe_management {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.iub_lfe_management to javafx.fxml;
    opens org.example.iub_lfe_management.shan to javafx.fxml;

    exports org.example.iub_lfe_management;
    exports org.example.iub_lfe_management.shan;
}
