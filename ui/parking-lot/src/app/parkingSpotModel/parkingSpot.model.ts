export class ParkingSpot {
    public parkingSpotId: {
        parkingLotId:number,
        floorNo:number,
        spotNo:number,
    }
    public spotSize:string;
    public vehicleNo:string;

    constructor(parkingSpotId:{
            parkingLotId:number,
            floorNo:number,
            spotNo:number,
    }, spotSize:string, vehicleNo:string) {
        this.parkingSpotId = parkingSpotId;
        this.spotSize = spotSize;
        this.vehicleNo = vehicleNo;
    }
}
