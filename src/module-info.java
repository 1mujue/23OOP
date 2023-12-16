module Final{
	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	
	opens applications to javafx.graphics, javafx.fxml;
}
