// @ts-ignore
import { AsyncWeather } from '@cicciosgamino/openweather-apis'

class WeatherService {
    private static async init(location: string): Promise<AsyncWeather> {
        const apiKey = '4d89e48d56095b661d80368e09fb7111';
        const weatherInstance = await new AsyncWeather();

        weatherInstance.setLang('fr');
        weatherInstance.setApiKey(apiKey);
        weatherInstance.setCity(location);
        return weatherInstance;
    }

    public static async pressure(location: string): Promise<number> {
        return (await WeatherService.init(location)).getPressure();
    }
    public static async temperature(location: string): Promise<number> {
        return (await WeatherService.init(location)).getTemperature();
    }
    public static async humidity(location: string): Promise<number> {
        return (await WeatherService.init(location)).getHumidity();
    }
    private static async allInformation(location: string): Promise<Object> {
        return (await WeatherService.init(location)).getAllWeather();
    }

    public static async wind(location: string): Promise<number> {
        return (await WeatherService.allInformation(location)).wind.speed;
    }

    public static async feelLike(location: string): Promise<number> {
        return (await WeatherService.allInformation(location)).main.feels_like;
    }

    public static async tempMin(location: string): Promise<number> {
        return (await WeatherService.allInformation(location)).main.temp_min;
    }

    public static async tempMax(location: string): Promise<number> {
        return (await WeatherService.allInformation(location)).main.temp_max;
    }

}

export default WeatherService;