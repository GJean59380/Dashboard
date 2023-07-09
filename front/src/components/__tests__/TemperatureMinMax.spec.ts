import { describe, it, expect } from "vitest";

import { mount } from "@vue/test-utils";
import WindSpeed from "@/components/weather/WindSpeed.vue";
import {WeatherType} from "../../types/WeatherType";

describe("TemperatureMinMax", () => {
    // Set element
    const wrapper = mount(WindSpeed, {
        props: { type: WeatherType.temperatureMinMax, city: "Paris", value: "20°, 21°" },
    });

    it("renders city properly", () => {
        expect(wrapper.text()).toContain("Paris");
    });

    it("renders temperature min and temperature max values properly", () => {
        expect(wrapper.text()).toContain("20°, 21°");
    });
});
