
<h1 align="center">   :zap: <img src="https://avatars.githubusercontent.com/u/1119453?s=200&v=4" width="60" height="60" > <a href="https://github.com/browserstack/browserstack-examples-junit5">BrowserStack Examples JUnit 5</a>  <img src="https://camo.githubusercontent.com/abbaedce4b226ea68b0fd43521472b0b146d5ed57956116f69752f43e7ddd7d8/68747470733a2f2f6a756e69742e6f72672f6a756e6974352f6173736574732f696d672f6a756e6974352d6c6f676f2e706e67" width="60" height="60" >
 :zap:</h1>

 # :label: [Introduction](https://github.com/browserstack/browserstack-examples-junit5#introduction) 

Welcome to BrowserStack JUnit 5 Examples, a sample UI testing framework empowered with **[Selenium](https://www.selenium.dev/)** and **[JUnit 5](https://junit.org/junit5/)**. Along with the framework the repository also contains a collection of sample test scripts written for **[BrowserStack Demo Application](https://bstackdemo.com/)**.

This repository includes a number of **[sample configuration files](/src/test/resources)** to run these on tests on various platforms including **on-premise browsers**, browsers running on a remote selenium grid such as **[BrowserStack Automate](https://www.browserstack.com/automate)** or in a **[Docker container](https://github.com/SeleniumHQ/docker-selenium)**. The framework and test scripts are configured to run with both **[Gradle](https://gradle.org/)** and **[Maven](https://maven.apache.org/)**. Starter **[gradle.build](/build.gradle)** and **[pom.xml](/pom.xml)** files are also included in the project.

<h1></h1>

 ## Tests Included in this Repository
 
 Following are the test cases included in this repository:

| Module   | Test Case                          | Description |
  | ---   | ---                                   | --- |
| E2E     | PurchaseTest                | This test scenario verifies successful product purchase lifecycle end-to-end. It demonstrates the [Page Object Model design pattern](https://www.browserstack.com/guide/page-object-model-in-selenium) and is also the default test executed in all the single test run profiles. |
| Login    | RedirectionTest        | This test verifies that the user needs to login to view the favourites marked by him or her. |
| Login    | Login as Locked User               | This test verifies the login workflow error for a locked user. |
| Offers   | Offers for Mumbai location     | This test mocks the GPS location for Mumbai and verifies that the product offers applicable for the Mumbai location are shown.   |
| Product  | Apply Apple Vendor Filter          | This test verifies that 9 Apple products are only shown if the Apple vendor filter option is applied. |
| Product  | Apply Lowest to Highest Order By   | This test verifies that the product prices are in ascending order when the product sort "Lowest to Highest" is applied. |
| User     | Login as User with no image loaded | This test verifies that the product images load for user: "image_not_loading_user" on the e-commerce application. Since the images do not load, the test case assertion fails.|
| User     | Login as User with existing Orders |  This test verifies that existing orders are shown for user: "existing_orders_user"  |
  
<h1> </h1>

 # :gear:  [Repository Setup](https://github.com/browserstack/browserstack-examples-junit5#repositorysetup)
 
 ## Prerequisites
 Ensure you have the following dependencies installed on the machine
 
 1. Java Development Kit (8 or above)
 2. Maven (3 or above) or Gradle ( 7 or above)
 3. Allure Command Line Tool or Allure Jenkins Plugin
 4. [Chrome Driver](https://chromedriver.chromium.org/) and [Chrome Browser](https://www.google.com/chrome/)   ![OnPrem](https://img.shields.io/badge/For-OnPrem-green)
 5. [Docker](https://www.docker.com/) and [Docker Selenium Grid](https://github.com/SeleniumHQ/docker-selenium).  ![OnDocker](https://img.shields.io/badge/For-OnDocker-blue)
 6. [BrowserStack Automate Account](https://www.browserstack.com/automate). ![BrowserStack](https://img.shields.io/badge/For-BrowserStackAutomate-orange)

 
  ## Setup with Maven ![Maven](https://img.shields.io/badge/With-maven-indigo)
 :pushpin: Clone this repository 
 <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code> git clone git@github.com:browserstack/browserstack-examples-junit5.git </code>
  <br/> <br/>
 :pushpin: Navigate to the repository directory
  <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>cd browserstack-examples-junit5</code>
 <br/> <br/>
 :pushpin: Install the required maven JARs
  <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>mvn install</code>
 
   ## Setup with Gradle ![Gradle](https://img.shields.io/badge/With-gradle-green)
 :pushpin: Clone this repository 
 <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code> git clone git@github.com:browserstack/browserstack-examples-junit5.git </code>
  <br/> <br/>
 :pushpin: Navigate to the repository directory
  <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>cd browserstack-examples-junit5</code>
  <br/> <br/>
 :pushpin: Install the required gradle JARs
  <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>gradle build</code>
 <br>
 
 # :wrench:  [Repository Configuration](https://github.com/browserstack/browserstack-examples-junit5#repositoryconfiguration)
 
The repository is designed to execute on any browser managed by a selenium webdriver. As an example the included configuration files are designed to run on the following three setups. 
1. On Premise Browsers
2. Browsers running in a docker container
3. Browsers on BrowserStack Automate

The repository is also designed to execute on both public as well as a locally hosted copy of the demo application. The prerequisites and setup details for all of these variations are labeled respectively.

 ## Configuring Concurrency Parameters ![Maven](https://img.shields.io/badge/With-maven-indigo) ![Gradle](https://img.shields.io/badge/With-gradle-green)

On BrowserStack Automate, you can spin multiple browser instances in parallel to reduce your over all build time. With JUnit 5 you configure the number concurrent test executions to an optimal count. In the sample POM or Gradle builld file in the repository it is set to a value 5 using the  <code>junit.jupiter.execution.parallel.config.fixed.parallelism</code> property. All such JUnit 5 configurations are gouped in these files together. Change these values as per your convenience to achieve the level of concurrency you desire.

 # :rocket:  [Test Execution](https://github.com/browserstack/browserstack-examples-junit5#testexecution)
 
 ## Test Execution Prerequisites [![OnPrem](https://img.shields.io/badge/For-OnPrem-green)]()
 
 :pushpin: Install [Chrome Driver](https://chromedriver.chromium.org/).
 <br/> <br/>
 :pushpin: Install [Chrome Browser](https://www.google.com/chrome/).
 <br/> <br/>
 :pushpin: Add the path of Chrome Driver in on-prem configuration fies [capabilities-on-prem.yml](/src/test/resources/capabilities-on-prem.yml) and [capabilities-on-prem-suite.yml](/src/test/resources/capabilities-on-prem-suite.yml) as <code>driverPath</code>.

 ## Test Execution Prerequisites [![OnDocker](https://img.shields.io/badge/For-OnDocker-blue)]()
 
 :pushpin: Install [Docker](https://www.docker.com/).
 <br/> <br/>
 :pushpin: Navigate to the repository directory.
 <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>cd browserstack-examples-junit5</code>
 <br/> <br/>
 :pushpin: Install the required docker images
  <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>docker-compose pull</code>
 <br/> <br/>
 :pushpin: Start the docker containers with selenium hub and browser nodes
  <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>docker-compose up -d</code>
 <br/> <br/>

 ## Test Execution Prerequisites [![BrowserStack](https://img.shields.io/badge/For-BrowserStackAutomate-orange)]()
 
 :pushpin: Create a new [BrowserStack account](https://www.browserstack.com/users/sign_up) or use an existing one.
 <br/> <br/>
 :pushpin: Identify your BrowserStack username and access key from the [BrowserStack Automate Dashboard](https://automate.browserstack.com/) and export them as environment variables using the below commands.
 <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  - For Unix-like or Mac machines:

  ```sh
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  - For Windows:

  ```shell
  set BROWSERSTACK_USERNAME=<browserstack-username>
  set BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  Alternatively, you can also add your username and access_key objects in the cloud driver [configuration files](/src/test/resources).

:page_facing_up: Note: We have configured a list of test capabilities in these [configuration files](/src/test/resources). You are free update them based on your device or browser test requirements. The exact test capability values can be easily identified using the [Browserstack Capability Generator](https://browserstack.com/automate/capabilities)

 ## Test Execution Prerequisites ![Local](https://img.shields.io/badge/For-Local-yellow)
 :pushpin: Clone the [BrowserStack demo application](https://github.com/browserstack/browserstack-demo-app) repository.
 <br/>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <code>git clone git@github.com:browserstack/browserstack-demo-app.git</code>
  <br/><br/>
  :pushpin: Please follow the README.md on the BrowserStack demo application repository to install and start the dev server on localhost.
   <br/><br/>
 :page_facing_up: Note: You may need to provide additional BrowserStackLocal arguments to successfully connect your localhost environment with BrowserStack infrastructure. (e.g if you are behind firewalls, proxy or VPN).
 
 Further details for successfully creating a BrowserStackLocal connection can be found here:

  - [Local Testing with Automate](https://www.browserstack.com/local-testing/automate)
  - [BrowserStackLocal Java GitHub](https://github.com/browserstack/browserstack-local-java)

 
 
 
## Test Execution Profiles ![Maven](https://img.shields.io/badge/With-maven-indigo) ![Gradle](https://img.shields.io/badge/With-gradle-green)

Following are the preconfigured test execution profiles available in the sample POM or Gradle build file.


  
<table>
 <tr>
  <th width='12%'>Profile</th>
  <th width='10%'>Description</th>
  <th width='10%'>Maven Command
  <br>
   <a href="#setup-with-maven-"><img src="https://img.shields.io/badge/Requires-Maven-indigo"/></a>
  </th>
  <th width='10%'>Gradle Command
  <br>
   <a href="#setup-with-gradle-"><img src="https://img.shields.io/badge/Requires-Gradle-green"/></a>
  </th>
  <th width='10%'>Configuration File</th>
 </tr>
 <tr>
  <td>on-prem
   <br>
   <a href="#test-execution-prerequisites-"><img src="https://img.shields.io/badge/Requires-OnPrem-green"/></a>
  </td>
  <td>Runs a single test on a Chrome browser instance on your own machine.</td>
  <td><code>mvn test on-prem</code></td>
  <td><code>gradle on-prem</code></td>
  <td>capabilities-on-prem.yml</td>
 </tr>
  <tr>
  <td>on-prem-suite
   <br>
   <a href="#test-execution-prerequisites-"><img src="https://img.shields.io/badge/Requires-OnPrem-green"/></a>
   </td>
  <td>Runs the entire test suite sequentially on Chrome browser instances, on your own machine.</td>
  <td><code>mvn test on-prem-suite</code></td>
  <td><code>gradle on-prem-suite</code></td>
  <td>capabilities-on-prem-suite.yml</td>
 </tr>
 
  <tr>
  <td>docker
   <br>
   <a href="#test-execution-prerequisites--1"><img src="https://img.shields.io/badge/Requires-OnDocker-blue"/></a>
   </td>
  <td>Runs a single test on a Firefox browser instance running in a Docker container.</td>
  <td><code>mvn test docker</code></td>
  <td><code>gradle docker</code></td>
  <td>capabilities-docker.yml</td>
 </tr>
 
  <tr>
  <td>docker-parallel
   <br>
   <a href="#test-execution-prerequisites--1"><img src="https://img.shields.io/badge/Requires-OnDocker-blue"/></a>
   </td>
  <td>Concurrently runs the entire test suite on a number of Firefox browser instances running in a Docker container.</td>
  <td><code>mvn test docker-parallel</code></td>
  <td><code>gradle docker-parallel</code></td>
  <td>capabilities-docker-parallel.yml</td>
 </tr>
 
 <tr>
  <td>bstack-single
  <br>
   <a href="#test-execution-prerequisites--2"><img src="https://img.shields.io/badge/Requires-BrowserStackAutomate-orange"/></a>
  </td>
  <td>Runs a single test on a single browser on BrowserStack.</td>
  <td><code>mvn test bstack-single</code></td>
  <td><code>gradle bstack-single</code></td>
  <td>capabilities-single.yml</td>
 </tr>
 
  <tr>
  <td>bstack-local
   <br>
   <a href="#test-execution-prerequisites--2"><img src="https://img.shields.io/badge/Requires-BrowserStackAutomate-orange"/></a>
   <br>
   <a href="#test-execution-prerequisites--3"><img src="https://img.shields.io/badge/Requires-Local-yellow"/></a>
   </td>
  <td>Runs a single test on a single browser on BrowserStack (On a copy demo application hosted on your internal environment or local machine).</td>
  <td><code>mvn test bstack-local</code></td>
  <td><code>gradle bstack-local</code></td>
  <td>capabilities-local.yml</td>
 </tr>
 
  <tr>
  <td>bstack-local-parallel
   <br>
   <a href="#test-execution-prerequisites--2"><img src="https://img.shields.io/badge/Requires-BrowserStackAutomate-orange"/></a>
    <br>
   <a href="#test-execution-prerequisites--3"><img src="https://img.shields.io/badge/Requires-Local-yellow"/></a>
   </td>
  <td>Concurrently runs the entire test suite on a single Browser type on BrowserStack (On a copy demo application hosted on your internal environment or local machine).</td>
  <td><code>mvn test bstack-local-parallel</code></td>
  <td><code>gradle bstack-local-parallel</code></td>
  <td>capabilities-local-parallel.yml</td>
 </tr>
 
 <tr>
  <td>bstack-local-parallel-browsers
  <br>
   <a href="#test-execution-prerequisites--2"><img src="https://img.shields.io/badge/Requires-BrowserStackAutomate-orange"/></a>
    <br>
   <a href="#test-execution-prerequisites--3"><img src="https://img.shields.io/badge/Requires-Local-yellow"/></a>
  </td>
  <td>Concurrently runs the entire test suite on a number of device/browser types on BrowserStack (On a copy demo application hosted on your internal environment or local machine).</td>
  <td><code>mvn test bstack-local-parallel-browsers</code></td>
  <td><code>gradle bstack-local-parallel-browsers</code></td>
  <td>capabilities-local-parallel-browsers.yml</td>
 </tr>
 
  <tr>
  <td>bstack-parallel
   <br>
   <a href="#test-execution-prerequisites--2"><img src="https://img.shields.io/badge/Requires-BrowserStackAutomate-orange"/></a>
   </td>
  <td>Concurrently runs the entire test suite on a single Browser type on BrowserStack.</td>
  <td><code>mvn test bstack-parallel</code></td>
  <td><code>gradle bstack-parallel</code></td>
  <td>capabilities-parallel.yml</td>
 </tr>
 
   <tr>
  <td>bstack-parallel-browsers
    <br>
   <a href="#test-execution-prerequisites--2"><img src="https://img.shields.io/badge/Requires-BrowserStackAutomate-orange"/></a>
    </td>
  <td>Concurrently runs the entire test suite on a number of device/browser types on BrowserStack.</td>
  <td><code>mvn test bstack-parallel-browsers</code></td>
  <td><code>gradle bstack-parallel-browsers</code></td>
  <td>capabilities-parallel-browsers.yml</td>
 </tr>
 </table>
 
 
 
 # :chart_with_upwards_trend:  [Test Results](https://github.com/browserstack/browserstack-examples-junit5#testresults)
 
 ## Viewing Allure Reports

 The repository is configured to generate Alllure of history with each execution. To view the results in an HTML format run the following command
  
  <code>allure serve allure-results</code>
 
 # :card_file_box: [Additional Resources](https://github.com/browserstack/browserstack-examples-junit5#additionalresources)

- View your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
- Documentation for writing [Automate test scripts in Java](https://www.browserstack.com/automate/java)
- Customizing your tests capabilities on BrowserStack using our [test capability generator](https://www.browserstack.com/automate/capabilities)
- [List of Browsers & mobile devices](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate) for automation testing on BrowserStack
- [Using Automate REST API](https://www.browserstack.com/automate/rest-api) to access information about your tests via the command-line interface
- Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)
- For testing public web applications behind IP restriction, [Inbound IP Whitelisting](https://www.browserstack.com/local-testing/inbound-ip-whitelisting) can be enabled with the [BrowserStack Enterprise](https://www.browserstack.com/enterprise) offering
