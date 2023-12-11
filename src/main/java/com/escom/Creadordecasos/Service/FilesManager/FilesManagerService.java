package com.escom.Creadordecasos.Service.FilesManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
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

    private String generateFileName(Long usuarioId, Long caseId, String extension) {
        // Implementa la lógica para generar un nombre de archivo único
        return "multimedia_" + usuarioId + "_" + caseId + "_" + System.currentTimeMillis() + "." + extension;
    }

    private String saveFile(MultipartFile file, String pathToSave, String fileName) throws IOException {
        // Guarda el archivo en el servidor
        String filePath = pathToSave + fileName;
        file.transferTo(new File(filePath));

        return filePath;
    }

    public String saveMultimedia(MultipartFile file, Long usuarioId, Long caseId) {
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


    public Boolean updateMultimedia(String rutaCompleta, MultipartFile nuevoArchivo) throws IOException {
        // Obtén el archivo existente
        File archivoExistente = new File(rutaCompleta);

        // Verifica si el archivo existe antes de intentar actualizarlo
        if (archivoExistente.exists()) {
            // Convierte el MultipartFile a un array de bytes
            byte[] bytesNuevos = nuevoArchivo.getBytes();

            // Utiliza FileOutputStream para escribir los nuevos bytes en el archivo existente
            try (FileOutputStream fileOutputStream = new FileOutputStream(archivoExistente)) {
                FileCopyUtils.copy(bytesNuevos, fileOutputStream);
            }

            System.out.println("Archivo actualizado con éxito.");
            return true;
        } else {
            System.out.println("El archivo no existe en la ruta especificada: " + rutaCompleta);
            return false;
        }
    }

    public Boolean deleteMultimedia(String rutaCompleta) {
        // Crea un objeto File con la ruta del archivo
        File archivo = new File(rutaCompleta);

        // Verifica si el archivo existe antes de intentar eliminarlo
        if (archivo.exists()) {
            // Intenta eliminar el archivo
            if (archivo.delete()) {
                System.out.println("Archivo eliminado con éxito.");
                return true;
            } else {
                System.out.println("No se pudo eliminar el archivo.");
                return false;
            }
        } else {
            System.out.println("El archivo no existe en la ruta especificada: " + rutaCompleta);
            return false;
        }
    }
}
