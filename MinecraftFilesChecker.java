
package com.craftlight.launcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class MinecraftFilesChecker {

    private MinecraftFilesChecker() {
        // Создавать экземпляр класса не нужно.
    }

    public static String check(String directory) {
        if (directory == null || directory.isBlank()) {
            return "ОШИБКА: папка Minecraft не выбрана.";
        }

        Path root;

        try {
            root = Path.of(directory).toAbsolutePath().normalize();
        } catch (Exception e) {
            return "ОШИБКА: некорректный путь к папке.";
        }

        if (!Files.isDirectory(root)) {
            return "Папка не найдена:\n" + root;
        }

        List<String> report = new ArrayList<>();
        report.add("ПРОВЕРКА ФАЙЛОВ MINECRAFT");
        report.add("");
        report.add("Папка: " + root);
        report.add("");

        checkFolder(root, "versions", report);
        checkFolder(root, "assets", report);
        checkFolder(root, "libraries", report);

        report.add("");
        report.add("Проверка завершена. Файлы не изменялись.");

        return String.join("\n", report);
    }

    private static void checkFolder(
            Path root,
            String folderName,
            List<String> report
    ) {
        Path folder = root.resolve(folderName);

        if (Files.isDirectory(folder)) {
            report.add("[OK] Найдена папка: " + folderName);
        } else {
            report.add("[!] Отсутствует папка: " + folderName);
        }
    }
}
