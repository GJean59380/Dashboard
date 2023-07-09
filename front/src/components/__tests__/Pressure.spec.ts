import { describe, it, expect } from "vitest";

import { mount } from "@vue/test-utils";
import WindSpeed from "@/components/weather/WindSpeed.vue";
import {WeatherType} from "../../types/WeatherType";

describe("Pressure", () => {
    // Set element
    const wrapper = mount(WindSpeed, {
        props: { type: WeatherType.pressure, city: "Paris", value: "20 hPa" },
    });

    it("renders city properly", () => {
        expect(wrapper.text()).toContain("Paris");
    });

    it("renders pressure value properly", () => {
        expect(wrapper.text()).toContain("20 hPa");
    });
});
