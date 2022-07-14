package TypewiseAlert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TypewiseAlertTest 
{
    private EmailDispatcher emailDispatcher;
    private DeviceController deviceController;
    private TypewiseAlert typewiseAlert;

    @Before
    public void setup() {
        emailDispatcher = new EmailDispatcher();
        deviceController = new DeviceController();
        typewiseAlert = new TypewiseAlert();
    }

    @Test
    public void infersBreachAsPerLimits()
    {
        assertSame(TemperatureBreachClassifier.inferBreach(12, 20, 30), BreachType.TOO_LOW);
        assertSame(TemperatureBreachClassifier.inferBreach(40, 20, 30), BreachType.TOO_HIGH);
        assertSame(TemperatureBreachClassifier.inferBreach(20, 20, 30), BreachType.NORMAL);
    }

    @Test
    public void getLimitsTest() {
        assertTrue(CoolingType.PASSIVE_COOLING.getLowTemp() == 0);
        assertTrue(CoolingType.PASSIVE_COOLING.getHighTemp() == 35);
        assertTrue(CoolingType.MED_ACTIVE_COOLING.getLowTemp() == 0);
        assertTrue(CoolingType.MED_ACTIVE_COOLING.getHighTemp() == 40);
        assertTrue(CoolingType.HI_ACTIVE_COOLING.getLowTemp() == 0);
        assertTrue(CoolingType.HI_ACTIVE_COOLING.getHighTemp() == 45);
    }

    @Test
    public void sendToEmailTargetTest() {
        String responseMsg = emailDispatcher.dispatchBreachMsgToTarget(BreachType.TOO_LOW);
        assertNotNull(responseMsg);
    }

    @Test
    public void sendToTargetControllerTest() {
        String controllerMsg = deviceController.dispatchBreachMsgToTarget(BreachType.TOO_HIGH);
        assertNotNull(controllerMsg);
    }

    @Test
    public void testControllerResponseForLowTemp() {
        BreachType breachType = BreachType.TOO_LOW;
        int header = 0xfeed;
        String controllerMsg = deviceController.dispatchBreachMsgToTarget(breachType);
        String expectedMsg = String.format("%s : %s",header,breachType.getBreachMsg());
        assertEquals(controllerMsg,expectedMsg);
    }

    @Test
    public void testControllerResponseForHighTemp() {
        BreachType breachType = BreachType.TOO_HIGH;
        int header = 0xfeed;
        String controllerMsg = deviceController.dispatchBreachMsgToTarget(breachType);
        String expectedMsg = String.format("%s : %s",header,breachType.getBreachMsg());
        assertEquals(controllerMsg,expectedMsg);
    }

    @Test
    public void testNormalTempMsgDispatch() {
        String controllerMsg = deviceController.dispatchBreachMsgToTarget(BreachType.NORMAL);
        assertNull(controllerMsg);
        String emailMsg = emailDispatcher.dispatchBreachMsgToTarget(BreachType.NORMAL);
        assertNull(emailMsg);
    }


    @Test
    public void testHighTempEmailDispatchMessages() {
       String recipient =  "a.b@c.com";
       BreachType highTempBreach = BreachType.TOO_HIGH;
       String actualMsg =  emailDispatcher.dispatchBreachMsgToTarget(highTempBreach);
       String expectedContent = String.format("To: %s\n %s",recipient,highTempBreach.getBreachMsg());
       assertEquals(actualMsg,expectedContent);
    }

    @Test
    public void testLowTempEmailDispatchMessages() {
        String recipient =  "a.b@c.com";
        BreachType lowTempBreach = BreachType.TOO_LOW;
        String actualMsg =  emailDispatcher.dispatchBreachMsgToTarget(lowTempBreach);
        String expectedContent = String.format("To: %s\n %s",recipient,lowTempBreach.getBreachMsg());
        System.out.println(expectedContent);
        assertEquals(actualMsg,expectedContent);
    }

    @Test
    public void testAlertDispatcherFactory() {

        IAlertTarget iAlertTarget = AlertDispatcherFactory.getRelevantDispatcherTarget(AlertTarget.TO_EMAIL);
        assertNotNull(iAlertTarget);
        iAlertTarget = AlertDispatcherFactory.getRelevantDispatcherTarget(AlertTarget.TO_EMAIL);
        assertNotNull(iAlertTarget);
        iAlertTarget = AlertDispatcherFactory.getRelevantDispatcherTarget(null);
        assertNull(iAlertTarget);
    }

    @Test
    public void testDispatchAlertFunctionality() {

      String responseMsg =   typewiseAlert.sendAlert(AlertTarget.TO_EMAIL,CoolingType.HI_ACTIVE_COOLING,17);
      assertNull(responseMsg);
      responseMsg = typewiseAlert.sendAlert(null,CoolingType.HI_ACTIVE_COOLING,69);
      assertNull(responseMsg);
      responseMsg = typewiseAlert.sendAlert(AlertTarget.TO_CONTROLLER,CoolingType.PASSIVE_COOLING,41);
      assertNotNull(responseMsg);
      responseMsg = typewiseAlert.sendAlert(AlertTarget.TO_CONTROLLER,CoolingType.MED_ACTIVE_COOLING,-29);
      assertNotNull(responseMsg);
      responseMsg = typewiseAlert.sendAlert(AlertTarget.TO_EMAIL,CoolingType.MED_ACTIVE_COOLING,-190);
      assertNotNull(responseMsg);
    }
}
