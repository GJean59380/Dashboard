import http from "@/services/HttpInfo";

class Bitcoin {
    private static method = { method: 'GET'};

    public static async getBitcoinPrice(): Promise<any>{
        return this.callApi("https://api.coindesk.com/v1/bpi/currentprice.json");
    }

    private static async callApi(url: string): Promise<any>{
        return fetch(url, this.method)
            .then(response => response.json())
            .then(jsonResponse => {return jsonResponse})
    }
}

export default Bitcoin;



