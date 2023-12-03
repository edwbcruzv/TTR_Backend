package com.escom.Creadordecasos.Service.FilesManager;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FilesManagerService {

    private String prepareSavePath(String folderName) {
        // Define la carpeta base para los archivos estáticos
        String baseFolder = "StaticFile";

        // Obtiene el separador de ruta según el sistema operativo
        String separator = File.separator;

        // Construye la ruta completa
        String pathToSave = baseFolder + separator + folderName + separator;

        // Verifica la existencia del directorio, si no existe, créalo
        File directory = new File(pathToSave);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        return pathToSave;
    }

    private String generateFileName(int usuarioId, int caseId, String extension) {
        // Implementa la lógica para generar un nombre de archivo único
        return "multimedia_" + usuarioId + "_" + caseId + "_" + System.currentTimeMillis() + "." + extension;
    }

    private String saveFile(MultipartFile file, String pathToSave, String fileName) throws IOException {
        // Guarda el archivo en el servidor
        String filePath = pathToSave + fileName;
        file.transferTo(new File(filePath));

        return filePath;
    }

    public String saveMultimedia(MultipartFile file, int usuarioId, int caseId) {
        try {
            // Obtén la extensión del archivo original
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);

            // Prepara la ruta de almacenamiento
            String folderName = "multimedia"; // Puedes personalizar el nombre de la carpeta
            String pathToSave = prepareSavePath(folderName);

            // Genera un nombre de archivo único
            String fileName = generateFileName(usuarioId, caseId, extension);

            // Guarda el archivo y devuelve la ruta del archivo guardado
            return saveFile(file, pathToSave, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de excepciones: puedes lanzar una excepción personalizada o devolver un mensaje de error, según tus necesidades
            return "Error al guardar el archivo.";
        }
    }
}
