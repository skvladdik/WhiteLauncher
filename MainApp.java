
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainApp extends Application {

    private static final String BLUE = "#3978F6";
    private static final String BACKGROUND = "#F4F7FC";

    private TextField nicknameField;
    private ComboBox<String> versionBox;
    private Label ramLabel;
    private Slider ramSlider;
    private Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND + ";");

        root.setLeft(createSidebar());

        VBox content = new VBox(22);
        content.setPadding(new Insets(35));
        content.getChildren().addAll(
                createHeader(),
                createWelcomeCard(),
                createGameSettings(),
                createPlayButton(),
                createFooter()
        );

        root.setCenter(content);

        Scene scene = new Scene(root, 1050, 720);

        stage.setTitle("WhiteLauncher");
        stage.setMinWidth(850);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    // Боковое меню
    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(30, 18, 25, 18));
        sidebar.setPrefWidth(220);
        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E8EDF5;" +
                "-fx-border-width: 0 1 0 0;"
        );

        Label logo = new Label("⬡ WhiteLauncher");
        logo.setFont(Font.font("Arial", 21));
        logo.setStyle("-fx-font-weight: bold; -fx-text-fill: #253653;");

        Label subtitle = new Label("MINECRAFT LAUNCHER");
        subtitle.setStyle(
                "-fx-text-fill: #9AA6B8;" +
                "-fx-font-size: 10px;"
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        sidebar.getChildren().addAll(
                logo,
                subtitle,
                new Separator(),
                menuButton("⌂", "Главная"),
                menuButton("▣", "Версии"),
                menuButton("⚙", "Настройки"),
                menuButton("♙", "Аккаунт"),
                menuButton("◇", "О лаунчере"),
                spacer,
                new Label("WhiteLauncher v1.0.0")
        );

        return sidebar;
    }

    private Button menuButton(String icon, String name) {
        Button button = new Button(icon + "   " + name);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(13));
        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #52627A;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"
        );

        button.setOnAction(e -> {
            showMessage(name, "Раздел " + name);
        });

        return button;
    }

    // Верхняя панель
    private VBox createHeader() {
        Label title = new Label("Главная");
        title.setFont(Font.font("Arial", 29));
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #253653;");

        Label description = new Label(
                "Добро пожаловать в WhiteLauncher!"
        );
        description.setStyle(
                "-fx-text-fill: #8794A8; -fx-font-size: 14px;"
        );

        return new VBox(7, title, description);
    }

    // Приветственный блок
    private VBox createWelcomeCard() {
        VBox card = new VBox(12);
        card.setPadding(new Insets(25));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E7EDF6;" +
                "-fx-border-radius: 18;"
        );

        Label welcome = new Label("ТВОЙ МИР MINECRAFT");
        welcome.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label heading = new Label("Время играть!");
        heading.setFont(Font.font("Arial", 27));
        heading.setStyle(
                "-fx-text-fill: #253653;" +
                "-fx-font-weight: bold;"
        );

        Label text = new Label(
                "Выбери версию, настрой игру и отправляйся в приключение."
        );
        text.setWrapText(true);
        text.setStyle("-fx-text-fill: #7F8DA3;");

        card.getChildren().addAll(welcome, heading, text);
        return card;
    }

    // Настройки игры
    private VBox createGameSettings() {
        VBox panel = new VBox(18);
        panel.setPadding(new Insets(25));
        panel.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E7EDF6;" +
                "-fx-border-radius: 18;"
        );

        Label title = new Label("Настройки игры");
        title.setFont(Font.font("Arial", 19));
        title.setStyle("-fx-font-weight: bold;");

        nicknameField = new TextField("Player123");
        nicknameField.setPromptText("Введите игровой ник");
        nicknameField.setPrefHeight(42);

        versionBox = new ComboBox<>();
        versionBox.getItems().addAll(
                "1.21.1",
                "1.21",
                "1.20.4",
                "1.20.1",
                "1.19.4",
                "1.18.2",
                "1.16.5",
                "1.12.2"
        );
        versionBox.setValue("1.21.1");
        versionBox.setMaxWidth(Double.MAX_VALUE);
        versionBox.setPrefHeight(42);

        ramLabel = new Label("Оперативная память: 4 ГБ");

        ramSlider = new Slider(2, 16, 4);
        ramSlider.setShowTickLabels(true);
        ramSlider.setShowTickMarks(true);
        ramSlider.setMajorTickUnit(2);
        ramSlider.setBlockIncrement(1);

        ramSlider.valueProperty().addListener(
                (obs, oldValue, newValue) -> {
                    int ram = newValue.intValue();
                    ramLabel.setText(
                            "Оперативная память: " + ram + " ГБ"
                    );
                }
        );

        panel.getChildren().addAll(
                title,
                new Label("Ник игрока"),
                nicknameField,
                new Label("Версия Minecraft"),
                versionBox,
                ramLabel,
                ramSlider
        );

        return panel;
    }

    // Кнопка запуска
    private VBox createPlayButton() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        Button play = new Button("▶     ИГРАТЬ");
        play.setMaxWidth(Double.MAX_VALUE);
        play.setPrefHeight(58);
        play.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 13;" +
                "-fx-cursor: hand;"
        );

        play.setOnAction(e -> {
            String nickname = nicknameField.getText().trim();

            if (nickname.isEmpty()) {
                showMessage("Ошибка", "Введи игровой ник!");
                return;
            }

            showMessage(
                    "WhiteLauncher",
                    "Выбрана версия Minecraft " +
                    versionBox.getValue() +
                    "\nИгрок: " + nickname +
                    "\nПамять: " +
                    (int) ramSlider.getValue() + " ГБ" +
                    "\n\nДля запуска игры необходимо подключить загрузчик Minecraft."
            );
        });

        Button check = new Button("Проверить файлы Minecraft");
        check.setMaxWidth(Double.MAX_VALUE);
        check.setPrefHeight(42);

        check.setOnAction(e -> checkMinecraftFiles());

        box.getChildren().addAll(play, check);
        return box;
    }

    // Проверка папки Minecraft
    private void checkMinecraftFiles() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Выбери папку Minecraft");

        File directory = chooser.showDialog(mainStage);

        if (directory == null) {
            return;
        }

        try {
            String result = MinecraftFilesChecker.check(
                    directory.getAbsolutePath()
            );

            showMessage("Результат проверки", result);

        } catch (Exception ex) {
            showMessage("Ошибка", ex.getMessage());
        }
    }

    // Нижняя панель
    private HBox createFooter() {
        Label status = new Label("● WhiteLauncher готов к работе");
        status.setStyle("-fx-text-fill: #16A36A;");

        HBox footer = new HBox(status);
        footer.setAlignment(Pos.CENTER_LEFT);

        return footer;
    }

    // Информационное окно
    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WhiteLauncher");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

}
