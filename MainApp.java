
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("CraftLight Launcher");
    alert.setHeaderText("Информация о компьютере");
    alert.setContentText(SystemInfo.getReport());
    alert.getDialogPane().setPrefWidth(480);
    alert.showAndWait();
}

private void checkMinecraftFiles(String minecraftDirectory) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("CraftLight Launcher");
    alert.setHeaderText("Результат проверки");
    alert.setContentText(
            MinecraftFilesChecker.check(minecraftDirectory)
    );
    alert.getDialogPane().setPrefWidth(520);
    alert.showAndWait();
}

private void showSystemInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("CraftLight Launcher");
    alert.setHeaderText("Информация о компьютере");
    alert.setContentText(SystemInfo.getReport());
    alert.getDialogPane().setPrefWidth(480);
    alert.showAndWait();



package ru.whitelauncher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final String BLUE = "#2878F8";
    private static final String TEXT = "#17243B";
    private static final String MUTED = "#7889A5";

    private BorderPane root;
    private VBox sidebar;
    private Label status;
    private ComboBox<String> versionBox;
    private Slider memorySlider;
    private Label memoryValue;
    private String currentPage = "Главная";

    @Override
    public void start(Stage stage) {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F8FD;");

        sidebar = createSidebar();
        root.setLeft(sidebar);
        root.setCenter(createHome());

        Scene scene = new Scene(root, 1250, 820);
        scene.getStylesheets().add(
                getClass().getResource("/styles.css").toExternalForm()
        );

        stage.setTitle("WhiteLauncher");
        stage.setMinWidth(950);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createSidebar() {
        VBox side = new VBox(12);
        side.setPadding(new Insets(26, 16, 20, 16));
        side.setPrefWidth(215);
        side.setStyle("-fx-background-color: white;");

        Label logo = new Label("⬡ WhiteLauncher");
        logo.setFont(Font.font("System", 20));
        logo.setStyle("-fx-font-weight: bold; -fx-text-fill: " + TEXT + ";");

        Label subtitle = new Label("Minecraft — просто и удобно");
        subtitle.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 11px;");

        VBox brand = new VBox(5, logo, subtitle);
        brand.setPadding(new Insets(4, 4, 24, 4));

        side.getChildren().add(brand);

        String[] pages = {
                "Главная", "Версии", "Настройки",
                "Аккаунты", "Моды", "Папка игры", "О лаунчере"
        };

        for (String page : pages) {
            Button button = new Button(page);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setAlignment(Pos.CENTER_LEFT);
            button.getStyleClass().add("nav-button");

            if (page.equals(currentPage)) {
                button.getStyleClass().add("nav-selected");
            }

            button.setOnAction(e -> showPage(page));
            side.getChildren().add(button);
        }

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Label footer = new Label("WhiteLauncher\nВерсия 1.0.0");
        footer.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 12px;");

        side.getChildren().addAll(spacer, footer);
        return side;
    }

    private void showPage(String page) {
        currentPage = page;
        root.setLeft(createSidebar());

        switch (page) {
            case "Главная" -> root.setCenter(createHome());
            case "Версии" -> root.setCenter(createVersionsPage());
            case "Настройки" -> root.setCenter(createSettingsPage());
            case "Аккаунты" -> root.setCenter(simplePage(
                    "Аккаунты", "Подключение Microsoft будет добавлено позже."
            ));
            case "Моды" -> root.setCenter(simplePage(
                    "Моды", "Здесь появится управление модами Minecraft."
            ));
            case "Папка игры" -> root.setCenter(simplePage(
                    "Папка игры",
                    "Открытие папки игры подключим на следующем этапе."
            ));
            default -> root.setCenter(simplePage(
                    "О WhiteLauncher",
                    "Собственный лаунчер Minecraft на Java 21."
            ));
        }
    }

    private VBox createHome() {
        VBox page = pageContainer();

        VBox hero = new VBox(10);
        hero.setPadding(new Insets(28));
        hero.setStyle(
                "-fx-background-color: linear-gradient(to right, #173A65, #3289EA);" +
                "-fx-background-radius: 18;"
        );

        Label welcome = new Label("Добро пожаловать\nв WhiteLauncher!");
        welcome.setStyle("-fx-text-fill: white; -fx-font-size: 27px;" +
                "-fx-font-weight: bold;");

        Label description = new Label(
                "Запускай Minecraft быстро, легко и с комфортом."
        );
        description.setStyle("-fx-text-fill: #E5F0FF; -fx-font-size: 14px;");

        hero.getChildren().addAll(welcome, description);

        VBox game = card("Настройки запуска");

        Label versionLabel = heading("Версия Minecraft");
        versionBox = new ComboBox<>();
        versionBox.getItems().addAll(
                "1.21.1", "1.21.4", "1.20.1", "1.19.4", "1.12.2"
        );
        versionBox.setValue("1.21.1");
        versionBox.setMaxWidth(Double.MAX_VALUE);

        Label memoryLabel = heading("Оперативная память");
        memorySlider = new Slider(2, 12, 4);
        memorySlider.setShowTickLabels(true);
        memorySlider.setMajorTickUnit(2);
        memorySlider.setBlockIncrement(1);

        memoryValue = new Label("4 ГБ");
        memoryValue.setStyle("-fx-text-fill: " + BLUE + "; -fx-font-weight: bold;");

        memorySlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            int memory = (int) Math.round(newValue.doubleValue());
            memoryValue.setText(memory + " ГБ");
        });

        HBox memoryHeader = new HBox(10, memoryLabel, memoryValue);
        memoryHeader.setAlignment(Pos.CENTER_LEFT);

        CheckBox mods = new CheckBox("Разрешить моды");
        mods.setSelected(true);

        CheckBox fullscreen = new CheckBox("Полноэкранный режим");
        CheckBox customJvm = new CheckBox("Выделенный JVM");
        customJvm.setSelected(true);

        VBox options = new VBox(12, mods, fullscreen, customJvm);

        Button launch = new Button("▶   Запустить Minecraft");
        launch.getStyleClass().add("launch-button");
        launch.setMaxWidth(Double.MAX_VALUE);
        launch.setOnAction(e -> {
            status.setText(
                    "Демонстрация: выбрана версия " + versionBox.getValue()
                    + ". Реальный запуск ещё не подключён."
            );
        });

        status = new Label("Готов к настройке");
        status.setWrapText(true);
        status.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 12px;");

        game.getChildren().addAll(
                versionLabel, versionBox,
                memoryHeader, memorySlider,
                new Separator(), options,
                launch, status
        );

        VBox news = card("Новости");

        Label newsTitle = heading("Добро пожаловать!");
        Label newsText = new Label(
                "Здесь будут новости Minecraft и обновления WhiteLauncher."
        );
        newsText.setWrapText(true);
        newsText.setStyle("-fx-text-fill: " + MUTED + ";");

        news.getChildren().addAll(newsTitle, newsText);

        HBox columns = new HBox(18, game, news);
        HBox.setHgrow(game, Priority.ALWAYS);
        HBox.setHgrow(news, Priority.ALWAYS);
        game.setPrefWidth(610);
        news.setPrefWidth(280);

        page.getChildren().addAll(hero, columns);
        return page;
    }

    private VBox createVersionsPage() {
        VBox page = pageContainer();
        VBox card = card("Управление версиями");

        Label info = new Label(
                "Выбери версию для будущей установки Minecraft."
        );
        info.setWrapText(true);

        ComboBox<String> versions = new ComboBox<>();
        versions.getItems().addAll(
                "1.21.1", "1.21.4", "1.20.1", "1.19.4", "1.12.2"
        );
        versions.setValue("1.21.1");
        versions.setMaxWidth(Double.MAX_VALUE);

        Button install = new Button("Подготовить установку");
        install.getStyleClass().add("launch-button");
        install.setOnAction(e -> showInfo(
                "Установка пока не подключена. Сейчас это интерфейс-прототип."
        ));

        card.getChildren().addAll(info, versions, install);
        page.getChildren().add(card);
        return page;
    }

    private VBox createSettingsPage() {
        VBox page = pageContainer();
        VBox card = card("Настройки лаунчера");

        CheckBox remember = new CheckBox("Сохранять настройки");
        remember.setSelected(true);

        CheckBox notifications = new CheckBox("Показывать уведомления");
        notifications.setSelected(true);

        Label info = new Label(
                "Сохранение настроек в файл добавим следующим этапом."
        );
        info.setWrapText(true);
        info.setStyle("-fx-text-fill: " + MUTED + ";");

        card.getChildren().addAll(remember, notifications, info);
        page.getChildren().add(card);
        return page;
    }

    private VBox simplePage(String title, String description) {
        VBox page = pageContainer();
        VBox card = card(title);

        Label info = new Label(description);
        info.setWrapText(true);
        info.setStyle("-fx-text-fill: " + MUTED + ";");

        card.getChildren().add(info);
        page.getChildren().add(card);
        return page;
    }

    private VBox pageContainer() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(28));
        page.setFillWidth(true);
        return page;
    }

    private VBox card(String title) {
        VBox box = new VBox(16);
        box.setPadding(new Insets(22));
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #E4EBF5;" +
                "-fx-border-radius: 16;"
        );

        Label titleLabel = heading(title);
        box.getChildren().add(titleLabel);
        return box;
    }

    private Label heading(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;"
        );
        return label;
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WhiteLauncher");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


private void showSystemInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("CraftLight Launcher");
    alert.setHeaderText("Информация о компьютере");
    alert.setContentText(SystemInfo.getReport());
    alert.getDialogPane().setPrefWidth(480);
    alert.showAndWait();
}

private void checkMinecraftFiles(String minecraftDirectory) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("CraftLight Launcher");
    alert.setHeaderText("Результат проверки");
    alert.setContentText(
            MinecraftFilesChecker.check(minecraftDirectory)
    );
    alert.getDialogPane().setPrefWidth(520);
    alert.showAndWait();
}
