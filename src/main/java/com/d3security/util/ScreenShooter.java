package com.d3security.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.d3security.util.webdriver.WebDriverBrowser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * This class contains the functionality related to the screenshooter
 *
 * @author Lucy
 */
public final class ScreenShooter {
	final static Logger logger = LogManager.getLogger(ScreenShooter.class);
	private static String dateFormat = "yyyyMMdHHsss";

	/**
	 * Capture a screen shot. This method will also add a screen shot to the
	 * Report of the current test via Reporter.log()
	 *
	 * @param driver
	 *            the driver to take the screen shot with
	 */
	public static void capture(final WebDriver driver) {

		if (driver == null) {
			Reporter.log("driver was null no screenshot taken");
			return;
		}
		final String fileName = String.format("%s%s",
				new SimpleDateFormat(dateFormat).format(new GregorianCalendar().getTime()),
				RandomStringUtils.randomAlphanumeric(6));

		if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals(WebDriverBrowser.IE)
				|| ((RemoteWebDriver) driver).getCapabilities().getBrowserName()
						.equals(WebDriverBrowser.INTERNET_EXPLORER)) {
			captureDesktopScreenshot();
			return;
		}

		File imageFile;
		try {
			imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		} catch (WebDriverException e) {
			final String message = String.format("Could not capture screenshot with driver (%s) to filename (%s)",
					driver.toString(), fileName);
			// If we can't take the screeenshot log it to the Reporter and our
			// logger
			Reporter.log(message);
			logger.error(message, e);
			return;
		}
		try {
			final String targetPngFileName = String.format("target/surefire-reports/html/%s.png", fileName);
			final String targetJpgFileName = String.format("target/surefire-reports/html/%s.jpg", fileName);
			final File newFile = new File(targetPngFileName);

			FileUtils.copyFile(imageFile, newFile.getAbsoluteFile());
			FileUtilities.compressPngToJpg(targetPngFileName, targetJpgFileName);

			logScreenShot(fileName, newFile.getAbsoluteFile());
			imageFile.delete();
		} catch (IOException e) {
			logger.error(String.format("Could not save screenshot %s", fileName), e);
		}
	}

	/**
	 * Capture Desktop Screenshot for IE Driver , since capture using WebDriver
	 * is showing blank screen . Added this method because of open bug in
	 * Selenium IE WebDriver for capturing screenshot in 32bit IE Driver
	 * (id-5876)
	 * 
	 */
	public static void captureDesktopScreenshot() {
		final String fileName = String.format("%s%s",
				new SimpleDateFormat(dateFormat).format(new GregorianCalendar().getTime()),
				RandomStringUtils.randomAlphanumeric(6));
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new BufferedImage(1040, 648, 1);
			// The height and width are considered as 1040 x 648 . The imageType is
			// 8-bit RGB color
			final String jpgFileName = String.format("target/surefire-reports/html/%s.jpg", fileName);
			File file = new File(jpgFileName);
			logger.info(String.format("Capturing screenshot of the desktop to %s", fileName ));
			capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "jpg", file);
			logScreenShot(fileName, file.getAbsoluteFile());
		} catch(Exception exception){
			logger.error(String.format("Could not capture screenshot  %s", fileName), exception);
		}
	}

	private static void logScreenShot(String fileName, File absoluteFile) {
		Reporter.log(String.format("<br>" + "<div style=\"text-align:left\">" + "<a href=\"%s.jpg\">"
				+ "<img src=\"%s.jpg\" alt=\"%s\" height=\"350\"/>" + "</a>" + "</div>" + "\n<br>", fileName,
				fileName, fileName));
		logger.info(String.format("Saving screenshot to %s", absoluteFile));
	}

	/**
	 * Save DOM - Document Object Model (DOM). Use this method to save Document
	 * Object Model. This method could be used if screenshot fails to capture and you would like to analyze the DOM
	 * 
	 * @param driver
	 */
	public static void saveDOM(final WebDriver driver) {
		if (driver == null) {
			Reporter.log("driver was null . Unable to save DOM.");
			return;
		}
		String dom = driver.getPageSource();
		final String fileName = String.format("%s%s",
				new SimpleDateFormat(dateFormat).format(new GregorianCalendar().getTime()),
				RandomStringUtils.randomAlphanumeric(6))
				+ ".txt";
		final String fileNameWithPath = String.format("target/surefire-reports/html/%s.jpg", fileName);
		File file = new File(fileNameWithPath);
		try {
			FileOutputStream io = new FileOutputStream(file);
			io.write(dom.getBytes());
			io.close();
			logger.info(String.format("Capturing DOM state to %s", file.getAbsoluteFile()));
		} catch (IOException e) {
			logger.error("Could not save DOM to file" , e);
		}
	}
}
