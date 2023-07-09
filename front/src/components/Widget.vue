<script lang="ts">
// TODO: Refactoring the component (too long)
import {defineComponent} from "vue";
import Today from "@/components/weather/Today.vue";
import WindSpeed from "@/components/weather/WindSpeed.vue";
import Top10 from "@/components/cheapShark/Top10.vue";
import TopGamesDeals from "@/components/cheapShark/TopGamesDeals.vue";
import {WeatherType} from "@/types/WeatherType";
import WeatherService from "@/services/WeatherService";
import CheapShark from "@/services/CheapShark";
import BitcoinPrice from "@/components/bitcoinPrice/BitcoinPrice.vue";
import Bitcoin from "@/services/OpenSky";
import ApiService from "@/services/ApiService";

export default defineComponent({
  name: "Widget",
  computed: {
    WeatherType() {
      return WeatherType
    }
  },
  components: {BitcoinPrice, TopGamesDeals, Top10, WindSpeed, Today},
  props: {
    widgetData: {
      type: Object,
      required: false,
    },
  },
  data() {
    return {
      id: 0,
      widget: "",
      state: "999",
      parameter: "",
      timer: 10,
      isCreated: false,
      value: new Array<any>(),
    };
  },
  created() {
    if (Object.keys(this.widgetData).length != 0) {
      const temp = {...this.widgetData};
      this.id = temp.id;
      this.widget = temp.widget;
      this.parameter = temp.parameter;
      this.state = temp.state;
      this.timer = setInterval(this.refreshData, 10 * 1000);
      this.isCreated = true;
    }
    this.value.push(0);
    this.value.push(0);
  },
  methods: {
    editState(value: string): void {
      this.state = value;
      console.log(this.state);
    },
    goToParameters(): void {
      if (
          this.widget === "Temperature" ||
          this.widget === "Wind" ||
          this.widget === "Pressure" ||
          this.widget === "Humidity" ||
          this.widget === "TempMax" ||
          this.widget === "FeelLike"
      )
        this.state = "2";
      else this.state = "3";
    },
    async setSettings() {
      this.state = "1";
      await this.refreshData();
      this.timer = setInterval(this.refreshData, this.timer * 1000);

      if (!this.isCreated) {
        const widgetsRef = await ApiService.getAll("/widget").then(response => {
          return response;
        });
        let widgetsUser = await ApiService.getAll("/user/whoami").then(({widgets}) => {
          return widgets;
        });
        let tempArr = [];

        for (let i = 0; i < widgetsUser.length; i++)
          tempArr.push(widgetsUser[i].widget.id)
        tempArr.push(widgetsRef.find((item: { name: any; }) => item.name === this.widget).id);
        await ApiService.update("/user", { widgets: tempArr });
      }
    },
    async resetWidget() {
      this.widget = "";
      this.state = "999";
      this.parameter =  "";
      clearInterval(this.timer);
      this.timer = 10;
      this.value = new Array<any>();

      let widgetsUser = await ApiService.getAll("/user/whoami").then(({widgets}) => {
        return widgets;
      });
      const idTemp = widgetsUser.indexOf(widgetsUser.find((e: { id: number; }) => e.widget.id === this.id));
      widgetsUser.splice(idTemp, 1);

      let tempArr = [];
      for (let i = 0; i < widgetsUser.length; i++)
        tempArr.push(widgetsUser[i].widget.id)
      await ApiService.update("/user", { widgets: tempArr });
      console.log(widgetsUser);
    },
    async refreshData() {
      console.log("je refresh ici avec " + this.widget);
      switch (this.widget) {
        case "Temperature":
          this.value[0] = await WeatherService.temperature(this.parameter);
          break;
        case "Pressure":
          this.value[0] = await WeatherService.pressure(this.parameter);
          break;
        case "Humidity":
          this.value[0] = await WeatherService.humidity(this.parameter);
          break;
        case "Wind":
          this.value[0] = await WeatherService.wind(this.parameter);
          break;
        case "TempMax":
          this.value[0] = await WeatherService.tempMin(this.parameter);
          this.value[1] = await WeatherService.tempMax(this.parameter);
          break;
        case "FeelLike":
          this.value[0] = await WeatherService.feelLike(this.parameter);
          break;
        case "BestDeal":
          this.value = await CheapShark.getBestDeal();
          break;
        case "BestGameInSale":
          this.value = await CheapShark.getBestGame();
          break;
        case "BitcoinPrice":
          const temp = await Bitcoin.getBitcoinPrice();
          this.value[0] = temp.bpi.EUR.rate;
          break;
        default:
          console.error("Wrong selected widget")
          break;
      }
    },
  },
});
</script>

<template>
  <div
      class="min-w-full h-32 min-h-full rounded shadow-xl flex items-center justify-center"
  >
    <!--- Add Widget -->
    <div v-if="state === '0'">
      <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-400">Type of widget
      <select @change="goToParameters()" v-model="widget" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
        <option disabled value="">Please select one</option>
        <option value="Temperature">Display today weather</option>
        <option value="Wind">Display wind speed</option>
        <option value="Pressure">Display pressure</option>
        <option value="Humidity">Display humidity</option>
        <option value="TempMax">Display temperature min and
          temperature max
        </option>
        <option value="FeelLike">Display feel likes temperature
        </option>
        <option value="BestDeal">Display top 10 best deals</option>
        <option value="BestGameInSale">Display top games on sale</option>
        <option value="BitcoinPrice">Display bitcoin price</option>
      </select>
      </label>
    </div>

    <!--- Settings with a parameter -->
    <div v-else-if="state === '2' || state === '3'">
      <div v-if="state === '2'">
        <label
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >Name
          <input
              type="text"
              name="parameter"
              class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Paris"
              v-model="parameter"
              required
          />
        </label>
      </div>
      <div>
        <label
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >Timer (in seconds)
          <input
              type="number"
              name="timer"
              class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="0"
              min="10"
              v-model="timer"
              required
          />
        </label>
      </div>
      <button
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full"
          @click="setSettings"
      >
        Set widget
      </button>
    </div>

    <!--- Component -->
    <WindSpeed
        :type="WeatherType.temperatureMinMax"
        :city="parameter"
        :value="value[0] + '°'"
        v-else-if="widget === 'Temperature' && state === '1'"
    />
    <WindSpeed
        :type="WeatherType.wind"
        :city="parameter"
        :value="+value[0] + 'km/h'"
        v-else-if="widget === 'Wind' && state === '1'"
    />
    <WindSpeed
        :type="WeatherType.pressure"
        :city="parameter"
        :value="+value[0] + ' hPa'"
        v-else-if="widget === 'Pressure' && state === '1'"
    />
    <WindSpeed
        :type="WeatherType.humidity"
        :city="parameter"
        :value="+value[0] + ' %'"
        v-else-if="widget === 'Humidity' && state === '1'"
    />
    <WindSpeed
        :type="WeatherType.temperatureMinMax"
        :city="parameter"
        :value="+value[1] + '°'"
        v-else-if="widget === 'TempMax' && state === '1'"
    />
    <WindSpeed
        :type="WeatherType.feelsLikeTemperature"
        :city="parameter"
        :value="+value[0] + '°'"
        v-else-if="widget === 'FeelLike' && state === '1'"
    />
    <Top10
        :gameLists="[...value]"
        v-else-if="widget === 'BestDeal' && state === '1'"
    />
    <TopGamesDeals
        :deals-list="[...value]"
        v-else-if="widget === 'BestGameInSale' && state === '1'"
    />
    <BitcoinPrice :value="value[0]" v-else-if="widget === 'BitcoinPrice' && state === '1'"/>

    <!--- No component -->
    <button @click="state = '0'" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" v-else>Add widget</button>

    <!--- Action buttons -->
    <div>
      <br><br><br><br><br>
      <button @click="goToParameters()" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded" v-if="state === '1'">Edit</button>
      <button @click="resetWidget()" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded" v-if="state === '1'">Delete</button>
    </div>
    </div>
</template>
