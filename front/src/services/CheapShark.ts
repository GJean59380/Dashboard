import http from "@/services/HttpInfo";

class CheapShark {
    private static method = { method: 'GET'};

    public static async getBestDeal(): Promise<Array<Object>>{
        return this.callApi("https://www.cheapshark.com/api/1.0/deals?storeID=1&pageSize=5");
    }
    public static async getBestGame(): Promise<Array<Object>>{
        return this.callApi("https://www.cheapshark.com/api/1.0/deals?storeID=1&pageSize=5&sortBy=Metacritic");
    }

    private static async callApi(url: string): Promise<any>{
        return fetch(url, this.method)
            .then(response => response.json())
            .then(jsonResponse => {return jsonResponse})
    }
}

export default CheapShark;