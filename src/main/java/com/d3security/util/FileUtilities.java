package com.d3security.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class contains all utility methods used to interact with files
 *
 * @author Lucy
 */
public final class FileUtilities {
    private final static Logger logger = LogManager
            .getLogger(FileUtilities.class);

    /**
     * Zips a source directory recursively
     *
     * @param sourceFolder  the source folder
     * @param outputZipFile the output zip file
     */
    public static void zipFile(final String sourceFolder,
                               final String outputZipFile) {
        final ZipTool zipTool = new ZipTool();
        zipTool.zipFolderRecursively(sourceFolder, outputZipFile);
    }

    /**
     * Takes an input png file and compress it to a jpg
     *
     * @param inputPngFileName  a string representing an input png file
     * @param outputJpgFileName a string representing an output jpg file
     * @param compressionRatio  the ratio of compression 0 - 1. (0 is good, 1 is perfect)
     */
    public static void compressPngToJpg(final String inputPngFileName,
                                        final String outputJpgFileName, final float compressionRatio) {
        final File sourcePng = new File(inputPngFileName);
        final File outputJpg = new File(outputJpgFileName);
        final File tempFile = new File(String.format("%s%s", System.getProperty("java.io.tmpdir"),
                outputJpg.getName()));
        ImagesUtilities.convertPngToJpg(sourcePng, tempFile);
        ImagesUtilities
                .compressJpegFile(outputJpg, outputJpg, compressionRatio);
        tempFile.delete();

    }

    /**
     * Takes an input png file and compress it to a jpg (at .75f compression
     * ratio)
     *
     * @param inputPngFileName
     * @param outputJpgFileName
     */
    public static void compressPngToJpg(final String inputPngFileName,
                                        final String outputJpgFileName) {
        final File sourcePng = new File(inputPngFileName);
        final File outputJpg = new File(outputJpgFileName);
        ImagesUtilities.convertPngToJpg(sourcePng, outputJpg);
        sourcePng.delete();
    }

    /**
     * Inner Class encapsulating FileUtilities "zipping" functionality
     *
     * @author Lucy
     */
    private final static class ZipTool {
        private List<String> fileList = new ArrayList<>();
        private String sourceFolder;

        /**
         * Zip it
         *
         * @param outputZipFile output ZIP file location
         */
        public void zipFolderRecursively(final String sourceFolder,
                                         final String outputZipFile) {
            this.sourceFolder = sourceFolder;
            generateFileList(new File(sourceFolder));

            final byte[] buffer = new byte[1024];
            FileOutputStream fileOutputStream;
            ZipOutputStream zipOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(outputZipFile);
                zipOutputStream = new ZipOutputStream(fileOutputStream);
                for (final String file : fileList) {
                    final ZipEntry zipEntry = new ZipEntry(file);
                    zipOutputStream.putNextEntry(zipEntry);
                    final FileInputStream fileInputStream = new FileInputStream(
                            sourceFolder + File.separator + file);
                    int len;
                    while ((len = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, len);
                    }
                    fileInputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            } finally {
                try {
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    logger.error(e);
                }
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    logger.error(e);
                }

            }
        }

        /**
         * Traverse a directory and get all files, and add the file into
         * fileList
         *
         * @param node file or directory
         */
        private void generateFileList(final File node) {

            // add file only
            if (node.isFile()) {
                fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
            }

            if (node.isDirectory()) {
                String[] subNote = node.list();
                for (final String filename : subNote) {
                    generateFileList(new File(node, filename));
                }
            }

        }

        /**
         * Format the file path for zip
         *
         * @param file file path
         * @return Formatted file path
         */
        private String generateZipEntry(final String file) {
            return file.substring(sourceFolder.length() + 1, file.length());
        }

    }

    /**
     * This class will hold all the functionality concerning image files
     *
     * @author javierv
     */
    private final static class ImagesUtilities {
        private final static Logger logger = LogManager
                .getLogger(ImagesUtilities.class);

        /**
         * Take a jpg and convert it to png
         *
         * @param pngFile       a source png file
         * @param outputJpgFile an output jpg file
         */
        public static void convertPngToJpg(final File pngFile,
                                           final File outputJpgFile) {

            // read image file
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(pngFile);
            } catch (IOException e) {
                logger.error(e);
                return;
            }

            // create a blank, RGB, same width and height, and a white
            // background
            final BufferedImage newBufferedImage = new BufferedImage(
                    bufferedImage.getWidth(), bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
                    Color.WHITE, null);

            // write to jpeg file
            try {
                ImageIO.write(newBufferedImage, "jpg", outputJpgFile);
            } catch (IOException e) {
                logger.error(e);
            }
        }

        /**
         * Takes a jpeg and compress it based on an input quantity
         *
         * @param infile             the source file
         * @param outfile            the output file
         * @param compressionQuality
         */
        public static void compressJpegFile(File infile, File outfile,
                                            float compressionQuality) {
            try {
                // Retrieve jpg image to be compressed
                final RenderedImage renderedImage = ImageIO.read(infile);

                // Find a jpeg writer
                ImageWriter imageWriter = null;
                final java.util.Iterator<ImageWriter> imageWriterIterator = ImageIO
                        .getImageWritersByFormatName("jpg");
                if (imageWriterIterator.hasNext()) {
                    imageWriter = (ImageWriter) imageWriterIterator.next();
                }

                // Prepare output file
                final ImageOutputStream imageOuputStream = ImageIO
                        .createImageOutputStream(outfile);
                imageWriter.setOutput(imageOuputStream);

                // Set the compression quality
                final JPEGImageWriteParam imageWriterParameters = new JPEGImageWriteParam(
                        Locale.getDefault());
                imageWriterParameters
                        .setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
                imageWriterParameters.setCompressionQuality(compressionQuality);

                // Write the image
                imageWriter.write(null,
                        new IIOImage(renderedImage, null, null),
                        imageWriterParameters);

                // Cleanup
                imageOuputStream.flush();
                imageWriter.dispose();
                imageOuputStream.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}
