import { describe, it, expect } from "vitest";

import { mount } from "@vue/test-utils";
import Today from "@/components/weather/Today.vue";

describe("Today", () => {
  // Set element
  const wrapper = mount(Today, {
    props: { icon: "sun", city: "Paris", temperature: "20" },
  });

  it("renders city properly", () => {
    expect(wrapper.text()).toContain("Paris");
  });

  it("renders temperature properly", () => {
    expect(wrapper.text()).toContain("20°C");
  });
});
