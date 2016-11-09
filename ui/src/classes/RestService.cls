@RestResource(urlMapping='/entity/*')
global with sharing class RestService {
        
    @HttpGet
    global static void setRequestJodDone(){
        List<Entity> entities = getAllTestEntity();
        for(Entity entity: entities){
            HttpService.insertData(JSON.serialize(entity));
        }
        String data = JSON.serialize(entities);
        System.debug(data);
    }
    
    public static Entity generateCarEntity(){
        Entity entity = new Entity();
        entity.name = 'cars';
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
        return entity;
    }
    
    public static Entity generatePhoneEntity(){
        Entity entity = new Entity();
        entity.name = 'Phones';
        entity.tableName = 'PHONE';
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
        
        Field producer = new Field();
            producer.name = 'Producer';
            producer.ColumnName = 'Producer';
            producer.type = 'NVARCHAR';
            producer.length = 100;
        	producer.unique = false;
        	fieldList.add(producer);
        
        Field model = new Field();
            model.name = 'Model';
            model.ColumnName = 'Model';
            model.type = 'NVARCHAR';
            model.length = 100;
        	model.unique = false;
        	fieldList.add(model);
        
        Field Operating_system = new Field();
            Operating_system.name = 'Operating_system';
            Operating_system.ColumnName = 'Operating_system';
            Operating_system.type = 'NVARCHAR';
            Operating_system.length = 100;
        	Operating_system.unique = false;
        	fieldList.add(Operating_system);
        
        Field Colour = new Field();
            Colour.name = 'Colour';
            Colour.ColumnName = 'Colour';
            Colour.type = 'NVARCHAR';
            Colour.length = 100;
        	Colour.unique = false;
        	fieldList.add(Colour);
        
        Field Screen_size = new Field();
            Screen_size.name = 'Screen_size';
            Screen_size.ColumnName = 'Screen_size';
            Screen_size.type = 'FLOAT';
            Screen_size.length = 0;
        	Screen_size.unique = false;
        	fieldList.add(Screen_size);
        
        Field Ram = new Field();
            Ram.name = 'RAM';
            Ram.ColumnName = 'RAM';
            Ram.type = 'INT';
            Ram.length = 0;
        	Ram.unique = false;
        	fieldList.add(Ram);
        
        Field Builtin_memory = new Field();
            Builtin_memory.name = 'Built_in_memory';
            Builtin_memory.ColumnName = 'Built_in_memory';
            Builtin_memory.type = 'INT';
            Builtin_memory.length = 0;
        	Builtin_memory.unique = false;
        	fieldList.add(Builtin_memory);
        
        Field Wireless_technology = new Field();
            Wireless_technology.name = 'Wireless_technology';
            Wireless_technology.ColumnName = 'Wireless_technology';
            Wireless_technology.type = 'NVARCHAR';
            Wireless_technology.length = 100;
        	Wireless_technology.unique = false;
        	fieldList.add(Wireless_technology);
        
        Field weight = new Field();
            weight.name = 'Weight';
            weight.ColumnName = 'Weight';
            weight.type = 'FLOAT';
            weight.length = 0;
        	weight.unique = false;
        	fieldList.add(weight);
        
        Field Width = new Field();
            Width.name = 'Width';
            Width.ColumnName = 'Width';
            Width.type = 'FLOAT';
            Width.length = 0;
        	Width.unique = false;
        	fieldList.add(Width);
        
        Field Height = new Field();
            Height.name = 'Height';
            Height.ColumnName = 'Height';
            Height.type = 'FLOAT';
            Height.length = 0;
        	Height.unique = false;
        	fieldList.add(Height);
        
        Field Depth = new Field();
            Depth.name = 'Depth';
            Depth.ColumnName = 'Depth';
            Depth.type = 'FLOAT';
            Depth.length = 0;
        	Depth.unique = false;
        	fieldList.add(Depth);
        
        Field Guarantee = new Field();
            Guarantee.name = 'Guarantee';
            Guarantee.ColumnName = 'Guarantee';
            Guarantee.type = 'NVARCHAR';
            Guarantee.length = 100;
        	Guarantee.unique = false;
        	fieldList.add(Guarantee);
        
        
        entity.fieldList = fieldList;
        return entity;
    }
    
    public static Entity generateVacuumCleanerEntity(){
        Entity entity = new Entity();
        entity.name = 'Vacuum_cleaners';
        entity.tableName = 'VACUUM_CLEANERS';
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
        
        Field Type = new Field();
            Type.name = 'Type';
            Type.ColumnName = 'Type';
            Type.type = 'NVARCHAR';
            Type.length = 100;
        	Type.unique = false;
        	fieldList.add(Type);
        
        Field CleaningType = new Field();
            CleaningType.name = 'CleaningType';
            CleaningType.ColumnName = 'Cleaning_Type';
            CleaningType.type = 'NVARCHAR';
            CleaningType.length = 100;
        	CleaningType.unique = false;
        	fieldList.add(CleaningType);
        
        Field Suction_power = new Field();
            Suction_power.name = 'Suction_power';
            Suction_power.ColumnName = 'Suction_power';
            Suction_power.type = 'INT';
            Suction_power.length = 0;
        	Suction_power.unique = false;
        	fieldList.add(Suction_power);
        
        Field Dust_collector_Volume = new Field();
            Dust_collector_Volume.name = 'Dust_collector_Volume';
            Dust_collector_Volume.ColumnName = 'Dust_collector_Volume';
            Dust_collector_Volume.type = 'FLOAT';
            Dust_collector_Volume.length = 0;
        	Dust_collector_Volume.unique = false;
        	fieldList.add(Dust_collector_Volume);
        
        Field CordLength = new Field();
            CordLength.name = 'CordLength';
            CordLength.ColumnName = 'Cord_Length';
            CordLength.type = 'FLOAT';
            CordLength.length = 0;
        	CordLength.unique = false;
        	fieldList.add(CordLength);
        
        Field Noise_level = new Field();
            Noise_level.name = 'Noise_level';
            Noise_level.ColumnName = 'Noise_level';
            Noise_level.type = 'FLOAT';
            Noise_level.length = 0;
        	Noise_level.unique = false;
        	fieldList.add(Noise_level);
        
        Field Adjustable_Power = new Field();
            Adjustable_Power.name = 'Adjustable_Power';
            Adjustable_Power.ColumnName = 'Adjustable_Power';
            Adjustable_Power.type = 'BIT';
            Adjustable_Power.length = 0;
        	Adjustable_Power.unique = false;
        	fieldList.add(Adjustable_Power);
        
        Field filterType = new Field();
            filterType.name = 'filterType';
            filterType.ColumnName = 'Filter_Type';
            filterType.type = 'NVARCHAR';
            filterType.length = 100;
        	filterType.unique = false;
        	fieldList.add(filterType);
        
        entity.fieldList = fieldList;
        return entity;
    }
    
    public static List<Entity> getAllTestEntity(){
        Entity car = generateCarEntity();
        car.id = 'carId';
        Entity phone = generatePhoneEntity();
        phone.id = 'phoneId';
        Entity cleaner = generateVacuumCleanerEntity();
        cleaner.id = 'cleanerId';
        List<Entity> entities = new List<Entity>();
        entities.add(car);
        entities.add(phone);
        entities.add(cleaner);
        return entities;
    }
}