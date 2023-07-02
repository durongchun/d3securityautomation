package com.d3security.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bsh.This;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Test reports listener
 * BeautifulReport 
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class TestReportListener implements IReporter {
    /**
     * Date 
     */
    private static final Date date = new Date();

    /**
     * Date format
     */
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMdHms");

    /**
     * Reports date
     */
    private static final String reportdate = simpleDateFormat.format(date);

    /**
     * Reports name
     */
    private static final String reportName = "D3Security Automation Test Reports-" + reportdate;

    /**
     * Reports template path
     */
    private final String templatePath = this.getClass().getResource("/").getPath() + "report" + File.separator + "template.html";

    /**
     * Reports path without file
     */
    private final String reportDirPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "test-output" + File.separator + "report";

    /**
     * Reports path with file
     */
    private final String reportPath = reportDirPath + File.separator + reportName + ".html";

    /**
     * Number of successful tests
     */
    private int testsPass;

    /**
     * Number of test failures
     */
    private int testsFail;

    /**
     * Number of test skips
     */
    private int testsSkip;

    /**
     * Begin time
     */
    private String beginTime;

    /**
     * Total time
     */
    private long totalTime;

    /**
     * Test report title
     */
    private final String project = "D3Security Automation Test Reports";

    /**
     * Reproduce reports
     *
     * @param xmlSuites       List<XmlSuite>
     * @param suites          List<ISuite>
     * @param outputDirectory String
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        log.info("Starts to produce BeautifulReport test reports");
        List<ITestResult> list = new ArrayList<>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                testsPass = testsPass + passedTests.size();
                IResultMap failedTests = testContext.getFailedTests();
                testsFail = testsFail + failedTests.size();
                IResultMap skippedTests = testContext.getSkippedTests();
                testsSkip = testsSkip + skippedTests.size();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                list.addAll(this.listTestResult(failedConfig));
            }
        }
        this.sort(list);
        this.outputResult(list);
    }

    /**
     * Convert IResultMap to ArrayList<ITestResult>
     *
     * @param resultMap IResultMap
     * @return ArrayList<ITestResult>
     */
    private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<>(results);
    }

    /**
     * Adjust order
     *
     * @param list List<ITestResult>
     */
    private void sort(List<ITestResult> list) {
        list.sort((r1, r2) -> r1.getStartMillis() < r2.getStartMillis() ? -1 : 1);
    }

    /**
     * Get total time
     *
     * @return long
     */
    public long getTime() {
        return totalTime;
    }

    /**
     * Output test report to template
     *
     * @param list List<ITestResult>
     */
    private void outputResult(List<ITestResult> list) {
        try {
            List<ReportInfo> listInfo = new ArrayList<>();
            int index = 0;
            for (ITestResult result : list) {
                String testName = result.getTestContext().getCurrentXmlTest().getName();
                if (index == 0) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    beginTime = formatter.format(new Date(result.getStartMillis()));
                    index++;
                }
                long spendTime = result.getEndMillis() - result.getStartMillis();
                totalTime += spendTime;
                String status = this.getStatus(result.getStatus());
                List<String> log = Reporter.getOutput(result);
                for (int i = 0; i < log.size(); i++) {
                    log.set(i, log.get(i).replaceAll("\"", "\\\\\""));
                }
                Throwable throwable = result.getThrowable();
                if (throwable != null) {
                    log.add(throwable.toString().replaceAll("\"", "\\\\\""));
                    StackTraceElement[] st = throwable.getStackTrace();
                    for (StackTraceElement stackTraceElement : st) {
                        log.add(("    " + stackTraceElement).replaceAll("\"", "\\\\\""));
                    }
                }
                ReportInfo info = new ReportInfo();
                info.setName(testName);
                info.setSpendTime(spendTime + "ms");
                info.setStatus(status);
                info.setClassName(result.getInstanceName());
                info.setMethodName(result.getName());
                info.setDescription(result.getMethod().getDescription());
                info.setLog(log);
                listInfo.add(info);
            }
            Map<String, Object> result = new HashMap<>();
            //result.put("testName", name);
            System.out.printf("Total test case running time：%.3f(min)\n", ((double)(totalTime/1000)/60));
            result.put("testName", this.project);
            result.put("testPass", testsPass);
            result.put("testFail", testsFail);
            result.put("testSkip", testsSkip);
            result.put("testAll", testsPass + testsFail + testsSkip);
            result.put("beginTime", beginTime);
            result.put("totalTime", totalTime + "ms");
            result.put("testResult", listInfo);
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            String template = this.read(reportDirPath, templatePath);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(reportPath)), StandardCharsets.UTF_8));
            assert template != null;
            template = template.replace("${resultData}", gson.toJson(result));
            output.write(template);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get status
     *
     * @param status int 
     * @return String 
     */
    private String getStatus(int status) {
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "PASS";
                break;
            case 2:
                statusString = "FAIL";
                break;
            case 3:
                statusString = "SKIP";
                break;
            default:
                break;
        }
        return statusString;
    }

    /**
     * Report info
     */
    public static class ReportInfo {
        /**
         * Test name
         */
        private String name;

        /**
         * Test class name
         */
        private String className;

        /**
         * Test method name
         */
        private String methodName;

        /**
         * Description
         */
        private String description;

        /**
         * Spend time during testing
         */
        private String spendTime;

        /**
         * Test status
         */
        private String status;

        /**
         * Test log list
         */
        private List<String> log;

        /**
         * Get name
         *
         * @return 
         */
        public String getName() {
            return name;
        }

        /**
         * Set name
         *
         * @param name 
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Get class name
         *
         * @return 
         */
        public String getClassName() {
            return className;
        }

        /**
         * Set class name
         *
         * @param className
         */
        public void setClassName(String className) {
            this.className = className;
        }

        /**
         * Get method name
         *
         * @return 
         */
        public String getMethodName() {
            return methodName;
        }

        /**
         * Set method name
         *
         * @param methodName 方法名
         */
        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        /**
         * Get spend time
         *
         * @return 
         */
        public String getSpendTime() {
            return spendTime;
        }

        /**
         * Set spend time
         *
         * @param spendTime 
         */
        public void setSpendTime(String spendTime) {
            this.spendTime = spendTime;
        }

        /**
         * Get status
         *
         * @return 
         */
        public String getStatus() {
            return status;
        }

        /**
         * Set status
         *
         * @param status 
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * Get log list
         *
         * @return 
         */
        public List<String> getLog() {
            return log;
        }

        /**
         * 设置日志列表
         *
         * @param log 日志列表
         */
        public void setLog(List<String> log) {
            this.log = log;
        }

        /**
         * Get description
         *
         * @return 
         */
        public String getDescription() {
            return description;
        }

        /**
         * Set description
         *
         * @param description 
         */
        public void setDescription(String description) {
            this.description = description;
        }

    }

    /**
     * Read template
     *
     * @param reportDirPath  template path without file
     * @param templatePath   template path with file
     * @return template contents
     */
    private String read(String reportDirPath, String templatePath) {
        // Create directory
        File reportDir = new File(reportDirPath);
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdirs();
        }
        File templateFile = new File(templatePath);
        InputStream inputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            inputStream = new FileInputStream(templateFile);
            int index;
            byte[] b = new byte[1024];
            while ((index = inputStream.read(b)) != -1) {
                stringBuffer.append(new String(b, 0, index));
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}