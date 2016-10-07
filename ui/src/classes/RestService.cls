@RestResource(urlMapping='/entity/*')
global with sharing class RestService {
    
    /*@HttpGet
    global static void setRequestJodDone(){
       Entity entity = new Entity();
        entity.name = 'clientTable';
        entity.tableName = 'PRODUCT';
        entity.schemaName = 'client';
        entity.fullUploadData = false;
        List<Field> fieldList = new List<Field>();
        for(Integer i = 1; i < 9; i++){
            Field field1 = new Field();
            field1.name = 'name' + i;
            field1.ColumnName = 'NAME' + i;
            field1.type = 'NVARCHAR';
            field1.length = 100;

            Field field2 = new Field();
            field2.name = 'quantity' + i;
            field2.ColumnName = 'QUANTITY' + i;
            field2.type = 'INT';
            field2.length = 0;

            Field field3 = new Field();
            field3.name  = 'isdiscount' + i;
            field3.ColumnName = 'ISDISCOUNT' + i;
            field3.type = 'BIT';
            field3.length = 0;

            Field field4 = new Field();
            field4.name = 'price' + i;
            field4.ColumnName = 'PRICE' + i;
            field4.type = 'FLOAT';
            field4.length = 0;

            fieldList.add(field1);
            fieldList.add(field2);
            fieldList.add(field3);
            fieldList.add(field4);
        }
        entity.fieldList = fieldList;
        String data = JSON.serialize(entity);
        System.debug(data);
        //HttpService.insertData(data);
    }*/
    
    @HttpGet
    global static void setRequestJodDone(){
        Entity entity = new Entity();
        entity.name = 'clientTable';
        entity.tableName = 'CARS';
        entity.schemaName = 'client';
        entity.fullUploadData = false;
        List<Field> fieldList = new List<Field>();
        
        Field identificationCode = new Field();
            identificationCode.name = 'Identification_code';
            identificationCode.ColumnName = 'Identification_code';
            identificationCode.type = 'INT';
            identificationCode.length = 0;
        	identificationCode.unique = true;
        	fieldList.add(identificationCode);
        
        Field brand = new Field();
            brand.name = 'Brand';
            brand.ColumnName = 'Brand';
            brand.type = 'NVARCHAR';
            brand.length = 100;
        	fieldList.add(brand);
        
        Field model = new Field();
            model.name = 'Model';
            model.ColumnName = 'Model';
            model.type = 'NVARCHAR';
            model.length = 100;
        	fieldList.add(model);
        
		 Field productionYear = new Field();
            productionYear.name = 'Production_Year';
            productionYear.ColumnName = 'Production_Year';
            productionYear.type = 'INT';
            productionYear.length = 0;
        	fieldList.add(productionYear);
        
        Field basketType = new Field();
            basketType.name = 'Basket_Type';
            basketType.ColumnName = 'Basket_Type';
            basketType.type = 'NVARCHAR';
            basketType.length = 100;
        	fieldList.add(basketType);
        
        Field engineType = new Field();
            engineType.name = 'Engine_Type';
            engineType.ColumnName = 'Engine_type';
            engineType.type = 'NVARCHAR';
            engineType.length = 100;
        	fieldList.add(engineType);
        
        Field fuelType = new Field();
            fuelType.name = 'Fuel_Type';
            fuelType.ColumnName = 'Fuel_type';
            fuelType.type = 'NVARCHAR';
            fuelType.length = 100;
        	fieldList.add(fuelType);
        
        Field engineSize = new Field();
            engineSize.name = 'Engine_size';
            engineSize.ColumnName = 'Engine_size';
            engineSize.type = 'FLOAT';
            engineSize.length = 0;
        	fieldList.add(engineSize);
        
        Field power = new Field();
            power.name = 'Power';
            power.ColumnName = 'Power';
            power.type = 'INT';
            power.length = 0;
        	fieldList.add(power);
        
        Field transmissionType = new Field();
            transmissionType.name = 'Transmission_Type';
            transmissionType.ColumnName = 'Transmission_type';
            transmissionType.type = 'NVARCHAR';
            transmissionType.length = 100;
        	fieldList.add(transmissionType);
        
        Field automaticTransmission = new Field();
            automaticTransmission.name = 'Automatic_Transmission';
            automaticTransmission.ColumnName = 'Automatic_transmission';
            automaticTransmission.type = 'BIT';
            automaticTransmission.length = 0;
        	fieldList.add(automaticTransmission);
        
        Field gearsNumber = new Field();
            gearsNumber.name = 'Gears_Number';
            gearsNumber.ColumnName = 'Gears_Number';
            gearsNumber.type = 'INT';
            gearsNumber.length = 0;
        	fieldList.add(gearsNumber);
        
        Field maximumSpeed = new Field();
            maximumSpeed.name = 'Maximum_Speed';
            maximumSpeed.ColumnName = 'Maximum_Speed';
            maximumSpeed.type = 'FLOAT';
            maximumSpeed.length = 0;
        	fieldList.add(maximumSpeed);
        
         Field acceleration = new Field();
            acceleration.name = 'Acceleration';
            acceleration.ColumnName = 'Acceleration';
            acceleration.type = 'FLOAT';
            acceleration.length = 0;
        	fieldList.add(acceleration);
        
		Field turbo = new Field();
            turbo.name = 'Turbo';
            turbo.ColumnName = 'Turbo';
            turbo.type = 'BIT';
            turbo.length = 0;
        	fieldList.add(turbo);

        Field seats = new Field();
            seats.name = 'seats';
            seats.ColumnName = 'Seats';
            seats.type = 'INT';
            seats.length = 0;
        	fieldList.add(seats);

		Field length = new Field();
            length.name = 'length';
            length.ColumnName = 'Length';
            length.type = 'INT';
            length.length = 0;
        	fieldList.add(length);

        Field width = new Field();
            width.name = 'width';
            width.ColumnName = 'Width';
            width.type = 'INT';
            width.length = 0;
            fieldList.add(width);
        
        Field height = new Field();
            height.name = 'height';
            height.ColumnName = 'Height';
            height.type = 'INT';
            height.length = 0;
            fieldList.add(height);
        
        Field wheelbase = new Field();
            wheelbase.name = 'wheelbase';
            wheelbase.ColumnName = 'Wheelbase';
            wheelbase.type = 'INT';
            wheelbase.length = 0;
            fieldList.add(wheelbase);
        
        Field frontTrack = new Field();
            frontTrack.name = 'Front_Track';
            frontTrack.ColumnName = 'Front_track';
            frontTrack.type = 'INT';
            frontTrack.length = 0;
            fieldList.add(frontTrack);
        
        Field reartrack = new Field();
            reartrack.name = 'Rear_Track';
            reartrack.ColumnName = 'Rear_track';
            reartrack.type = 'INT';
            reartrack.length = 0;
            fieldList.add(reartrack);
        
        Field curbWeight = new Field();
            curbWeight.name = 'Curb_weight';
            curbWeight.ColumnName = 'Curb_weight';
            curbWeight.type = 'INT';
            curbWeight.length = 0;
            fieldList.add(curbWeight);
        
        Field grossVehicleWeight = new Field();
            grossVehicleWeight.name = 'Gross_vehicle_weight';
            grossVehicleWeight.ColumnName = 'Gross_vehicle_weight';
            grossVehicleWeight.type = 'INT';
            grossVehicleWeight.length = 0;
            fieldList.add(grossVehicleWeight);
        
        Field cargoVolume = new Field();
            cargoVolume.name = 'Cargo_Volume';
            cargoVolume.ColumnName = 'Cargo_Volume';
            cargoVolume.type = 'INT';
            cargoVolume.length = 0;
            fieldList.add(cargoVolume);
        
        Field fuelTank = new Field();
            fuelTank.name = 'Fuel_tank';
            fuelTank.ColumnName = 'Fuel_tank';
            fuelTank.type = 'INT';
            fuelTank.length = 0;
            fieldList.add(fuelTank);
        
        Field producingCountry = new Field();
            producingCountry.name = 'Producing_country';
            producingCountry.ColumnName = 'Producing_country';
            producingCountry.type = 'NVARCHAR';
            producingCountry.length = 100;
            fieldList.add(producingCountry);
        
        
        
        Field price = new Field();
            price.name = 'Price';
            price.ColumnName = 'Price';
            price.type = 'FLOAT';
            price.length = 0;
            fieldList.add(price);     
                
        
        entity.fieldList = fieldList;
        String data = JSON.serialize(entity);
        System.debug(data);
    }
}