import java.util.regex.Pattern;

/**
 * Converts Celsius to Fahrenheit and vice-versa
 *
 * @author Will Goodman
 */
public class TemperatureScaleConverter {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: 'java Temperature $OUTPUT_SCALE $INPUT_TEMPERATURE', where the output scale is either 'f' or 'c' and the temperature is an integer");
        } else {

            String outputScale = args[0];
            String temperatureString = args[1];

            if (!outputScale.equals("f") && !outputScale.equals("c")) {
                System.out.println("Output scale must be either 'f' or 'c'");
            } else if (!Pattern.matches("^-?\\d+$", temperatureString)) {
                System.out.println("Temperature must be an integer");
            } else {
                int originalTemperature = Integer.parseInt(temperatureString);

                switch (outputScale) {
                    case "f":
                        System.out.println(originalTemperature + " C is " + celsiusToFahrenheit(originalTemperature) + " F.");
                        break;
                    case "c":
                        System.out.println(originalTemperature + " F is " + fahrenheitToCelsius(originalTemperature) + " C.");
                        break;
                }
            }
        }
    }

    /**
     * Converts Celsius to Fahrenheit
     *
     * @param celsius Integer temperature in Celsius
     * @return float Temperature converted to Fahrenheit
     */
    private static float celsiusToFahrenheit(int celsius) {
        return ((celsius * 9) / 5) + 32;
    }

    /**
     * Converts Fahrenheit to Celsius
     *
     * @param fahrenheit Integer temperature in Fahrenheit
     * @return float Temperature converted to Celsius
     */
    private static float fahrenheitToCelsius(int fahrenheit) {
        return ((fahrenheit - 32) * 5) / 9;
    }
}
