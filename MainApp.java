
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

private void showSystemInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("CraftLight Launcher");
    alert.setHeaderText("Информация о компьютере");
    alert.setContentText(SystemInfo.getReport());
    alert.getDialogPane().setPrefWidth(480);
    alert.showAndWait();


.

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
