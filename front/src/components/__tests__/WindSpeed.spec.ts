import { describe, it, expect } from "vitest";

import { mount } from "@vue/test-utils";
import WindSpeed from "@/components/weather/WindSpeed.vue";
import {WeatherType} from "../../types/WeatherType";

describe("WindSpeed", () => {
  // Set element
  const wrapper = mount(WindSpeed, {
    props: { type: WeatherType.wind, city: "Paris", value: "20 km/h" },
  });

  it("renders city properly", () => {
    expect(wrapper.text()).toContain("Paris");
  });

  it("renders wind value properly", () => {
    expect(wrapper.text()).toContain("20 km/h");
  });
});
