import {describe, it, expect} from "vitest";

import {mount} from "@vue/test-utils";
import WidgetsGrid from "@/components/WidgetsGrid.vue";
import Widget from "@/components/Widget.vue";

function fillArray(array: Array<object>) {
    for (let i = array.length; i < 9; i++)
        array.push({});
    return array;
}

describe("WidgetsGrid", () => {
    // Create array with three widgets
    let widgets = [{}, {}, {}];
    // Set element
    const wrapper = mount(WidgetsGrid, {
        props: {
            widgets: fillArray(widgets)
        },
    });

    it("renders number of widgets properly", () => {
        expect(wrapper.findAllComponents(Widget).length).equals(9);
    });
});
