import { describe, it, expect } from "vitest";

import { mount } from "@vue/test-utils";
import WindSpeed from "@/components/weather/WindSpeed.vue";
import {WeatherType} from "../../types/WeatherType";

describe("Humidity", () => {
    // Set element
    const wrapper = mount(WindSpeed, {
        props: { type: WeatherType.feelsLikeTemperature, city: "Paris", value: "20°C" },
    });

    it("renders city properly", () => {
        expect(wrapper.text()).toContain("Paris");
    });

    it("renders feels like temperature value properly", () => {
        expect(wrapper.text()).toContain("20°C");
    });
});
