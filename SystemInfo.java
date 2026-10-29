
package com.craftlight.launcher;

public final class SystemInfo {

    private SystemInfo() {
        // Создавать экземпляр класса не нужно.
    }

    public static String getReport() {
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);

        String osName = System.getProperty("os.name", "Неизвестно");
        String osVersion = System.getProperty("os.version", "Неизвестно");
        String osArch = System.getProperty("os.arch", "Неизвестно");
        String javaVersion = System.getProperty("java.version", "Неизвестно");
        String javaVendor = System.getProperty("java.vendor", "Неизвестно");

        return """
                ИНФОРМАЦИЯ О СИСТЕМЕ

                Операционная система: %s
                Версия Windows: %s
                Архитектура: %s

                Версия Java: %s
                Поставщик Java: %s

                Максимальная память JVM: %d МБ
                Выделено JVM: %d МБ
                Свободно в выделенной памяти: %d МБ
                """.formatted(
                osName,
                osVersion,
                osArch,
                javaVersion,
                javaVendor,
                maxMemory,
                totalMemory,
                freeMemory
        );
    }
}
