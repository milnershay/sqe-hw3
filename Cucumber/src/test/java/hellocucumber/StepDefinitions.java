package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private WebDriver StudDriver;
    private WebDriver TeachDriver;
    private WebDriverWait TeachWait;
    private WebDriverWait StudWait;
    private String Teacher_Username = "teacher";
    private String Password = "Aa!12345";
    private String Student_Username = "student";

    // init student driver
    public void initStudent(){
        String webDriver = "webdriver.edge.driver";
        String path = "C://EdgeDriver.exe";
        System.setProperty(webDriver, path);
        StudDriver = new EdgeDriver();
        StudWait = new WebDriverWait(StudDriver, Duration.ofSeconds(40));
        StudDriver.get("http://localhost/");
        StudDriver.manage().window().maximize();
        StudDriver.findElement(By.linkText("התחברות")).click();
    }

    // init teacher driver
    public void initTeacher(){
        String webDriver = "webdriver.edge.driver";
        String path = "C://EdgeDriver.exe";
        System.setProperty(webDriver, path);
        TeachDriver = new EdgeDriver();
        TeachWait = new WebDriverWait(TeachDriver, Duration.ofSeconds(40));
        TeachDriver.get("http://localhost/");
        TeachDriver.manage().window().maximize();
        TeachDriver.findElement(By.linkText("התחברות")).click();
    }

    // login function
    public void login(String username, WebDriver MyDriver){
        MyDriver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        MyDriver.findElement(By.xpath("//*[@id='password']")).sendKeys(Password);
        MyDriver.findElement(By.id("loginbtn")).click();
    }

    // $$ Start driver on login page $$
    @Given("student is on homepage")
    public void startStudentHomepage() {
        initStudent();
    }

    // Student logs in using username and password and navigates in moodle to quiz
    @When("student logs in and navigates to quiz")
    public void studentLoginAndNavigate() {
        login(Student_Username, StudDriver);
        //load quiz page after login
        StudDriver.get("http://localhost/mod/quiz/view.php?id=2");
    }

    @And("student enters quiz")
    public void studentStartQuiz(){
        StudDriver.findElement(By.xpath("//*[@id=\"region-main\"]/div[2]/div[1]/div/div")).click();
    }

    @And("student answers d")
    public void answerQuestion_d(){
        StudWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/div[2]/div[1]/div[4]/input"))).click();
    }

    @And("student answers a")
    public void answerQuestion_a(){
        StudWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/input"))).click();
    }

    @And("student finishes quiz")
    public void finishQuiz(){
        StudDriver.findElement(By.name("next")).click();
        StudDriver.findElement(By.xpath("//*[@id=\"region-main\"]/div[2]/div[5]/div/div")).click();
        StudWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[2]/div/div[2]/input[1]"))).click();
    }

    // $$ Checking that grade recieved was 0 $$
    @Then("grade should be 0")
    public void checkGradeIsZero() {
        String g = StudWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/table/tbody/tr[6]/td/b[1]"))).getText();
        float grade = Float.valueOf(g);
        assertEquals(0.0,grade);
        StudDriver.close();
    }

    // checks that grace recived was 10.0
    @Then("grade should be 10")
    public void checkGradeIsTen() {
        String g = StudWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/table/tbody/tr[6]/td/b[1]"))).getText();
        float grade = Float.valueOf(g);
        assertEquals(10.0,grade);
        StudDriver.close();
    }



    @Given("teacher is on homepage")
    public void startTeacherHomepage() {
        initTeacher();
    }

    @When("teacher logs in and goes to quiz")
    public void teacherLoginAndNavigate() {
        login(Teacher_Username, TeachDriver);
        //load quiz page after login
        TeachDriver.get("http://localhost/mod/quiz/view.php?id=2");
    }

    @And("teacher edits questions")
    public void editQuestions(){
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/div[2]/nav/ul/li[3]/a"))).click();
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div[3]/div/section/div[2]/div/ul/li/div/ul/li[2]/div/div/div[2]/a"))).click();
    }

    @And("teacher changes answer from a to d")
    public void editAnswer(){
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_fraction_0\"]")));
        Select gradeA =new Select(TeachDriver.findElement(By.xpath("//*[@id=\"id_fraction_0\"]")));
        gradeA.selectByValue("0.0");
        Select gradeD = new Select(TeachDriver.findElement(By.xpath("//*[@id=\"id_fraction_3\"]")));
        gradeD.selectByValue("1.0");
        TeachDriver.findElement(By.xpath("//*[@id=\"id_submitbutton\"]")).click();
    }

    @And("teacher changes answer from d to a")
    public void editAnswer2(){
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_fraction_0\"]")));
        Select gradeA =new Select(TeachDriver.findElement(By.xpath("//*[@id=\"id_fraction_0\"]")));
        gradeA.selectByValue("1.0");
        Select gradeD = new Select(TeachDriver.findElement(By.xpath("//*[@id=\"id_fraction_3\"]")));
        gradeD.selectByValue("0.0");
        TeachDriver.findElement(By.xpath("//*[@id=\"id_submitbutton\"]")).click();
    }

    // teacher answers quiz and checks that grade fits correct answer
    @Then("correct answer should be d")
    public void checkD(){
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div[2]/nav/ul/li[1]"))).click();
        TeachDriver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div[2]/div[1]/div/div/form/button")).click();
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[5]/div/div[3]/div/section/div[2]/form/div/div[1]/div[2]/div/div[2]/div[1]/div[4]/input"))).click();
        TeachDriver.findElement(By.name("next")).click();
        TeachDriver.findElement(By.xpath("//*[@id=\"region-main\"]/div[2]/div[5]/div/div")).click();
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[2]/div/div[2]/input[1]"))).click();
        String g = TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[5]/div/div[3]/div/section/div[2]/table/tbody/tr[6]/td/b[1]"))).getText();
        float grade = Float.valueOf(g);
        assertEquals(10.0,grade);
        TeachDriver.close();
    }


    // teacher answers quiz and checks that grade fits correct answer
    @Then("correct answer should be a")
    public void checkA(){
        TeachDriver.findElement(By.xpath("/html/body/div[4]/div[4]/div/div[2]/nav/ul/li[1]")).click();
        TeachDriver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div[2]/div[1]/div/div/form/button")).click();
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[5]/div/div[3]/div/section/div[2]/form/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/input"))).click();
        TeachDriver.findElement(By.name("next")).click();
        TeachDriver.findElement(By.xpath("//*[@id=\"region-main\"]/div[2]/div[5]/div/div")).click();
        TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[2]/div/div[2]/input[1]"))).click();
        String g = TeachWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[5]/div/div[3]/div/section/div[2]/table/tbody/tr[6]/td/b[1]"))).getText();
        float grade = Float.valueOf(g);
        assertEquals(10.0,grade);
        TeachDriver.close();
    }
}

