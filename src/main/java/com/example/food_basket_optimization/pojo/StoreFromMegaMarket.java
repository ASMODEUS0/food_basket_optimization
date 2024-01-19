package com.example.food_basket_optimization.pojo;


import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
@Setter
public  class StoreFromMegaMarket{
    public Store store;


    public static class Appearance{
        public String background_color;
        public String image_color;
        public boolean black_theme;
        public String logo_image;
        public String side_image;
        public String mini_logo_image;
    }

    public static class City{
        public int id;
        public String name;
        public String name_in;
        public String name_from;
        public String name_to;
        public String slug;
    }

    public static class Config{
        public String lifepay_identifier;
        public String import_key_postfix;
        public int seconds_for_assembly_item;
        public int additional_seconds_for_assembly;
    }

    public static class DeliveryTime{
        public String start;
        public String end;
        public int orders_limit;
        public int surge_amount;
        public int shipment_min_kilos;
        public int shipment_max_kilos;
        public int shipments_excess_kilos;
        public int shipments_excess_items_count;
        public int closing_time_gap;
        public String kind;
        public ArrayList<String> store_zone_ids;
        public ArrayList<Object> slot_active_days;
        public Object external;
    }

    public static class Location{
        public int id;
        public String full_address;
        public String city;
        public String street;
        public String building;
        public String block;
        public Object floor;
        public Object apartment;
        public Object entrance;
        public Object elevator;
        public Object region;
        public Object comments;
        public Object phone;
        public Object area;
        public Object settlement;
        public double lat;
        public double lon;
        public Object city_kladr_id;
        public Object street_kladr_id;
        public Object user_id;
        public Object door_phone;
        public Object kind;
        public boolean delivery_to_door;
    }

    public static class OperationalZone{
        public int id;
        public String name;
    }

    public static class PaymentMethod{
        public int id;
        public String name;
        public String environment;
        public String key;
    }

    public static class PaymentMethodsStore{
        public int id;
        public int store_id;
        public String tenant_id;
        public PaymentMethod payment_method;
    }

    public static class Retailer{
        public int id;
        public String name;
        public String color;
        public String secondary_color;
        public String logo;
        public String logo_background_color;
        public String slug;
        public String description;
        public String icon;
        public boolean is_alcohol;
        public boolean is_agent_contract_types;
        public int home_page_departments_depth;
        public boolean is_certificates;
        public Appearance appearance;
        public ArrayList<Object> services;
    }



    public static class ShippingMethod{
        public String name;
        public String kind;
        public int id;
    }

    public class Store{
        public int id;
        public String uuid;
        public String name;
        public int city_id;
        public boolean on_demand;
        public boolean on_demand_raw;
        public String time_zone;
        public Date available_on;
        public boolean has_conveyor;
        public boolean auto_routing;
        public boolean express_delivery;
        public String pickup_instruction;
        public String import_key_postfix;
        public boolean active;
        public boolean box_scanning;
        public int seconds_for_assembly_item;
        public int additional_seconds_for_assembly;
        public String retailer_store_id;
        public boolean external_assembly;
        public boolean training;
        public Object delivery_forecast_text;
        public Object pharmacy_legal_info;
        public String phone;
        public String orders_api_integration_type;
        public String opening_time;
        public String closing_time;
        public boolean parallel_assembly;
        public boolean assembly_dispatch;
        public boolean available_for_pickup;
        public Retailer retailer;
        public Location location;
        public ArrayList<StoreZone> store_zones;
        public Config config;
        public StoreSchedule store_schedule;
        public OperationalZone operational_zone;
        public City city;
        public ArrayList<Tenant> tenants;
        public ArrayList<StoreShippingMethod> store_shipping_methods;
        public ArrayList<PaymentMethodsStore> payment_methods_stores;
        public ArrayList<Object> licenses;
    }

    public static class StoreSchedule{
        public int id;
        public int store_id;
        public Template template;
    }

    public static class StoreShippingMethod{
        public int id;
        public int store_id;
        public String tenant_id;
        public Date available_on;
        public ShippingMethod shipping_method;
    }

    public static class StoreZone{
        public int id;
        public String name;
        public ArrayList<ArrayList<ArrayList<Double>>> area;
    }

    public static class Template{
        public ArrayList<DeliveryTime> delivery_times;
    }

    public static class Tenant{
        public String id;
        public String name;
        public String hostname;
        public String preferred_card_payment_method;
    }
}

