package by.ibn.alexamqttbridge.controllers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ibn.alexamqttbridge.resources.Event;
import by.ibn.alexamqttbridge.resources.PayloadDiscoveryResponse;
import by.ibn.alexamqttbridge.resources.Request;
import by.ibn.alexamqttbridge.resources.Response;

@Service
public class EventProcessorDiscovery extends EventProcessor {
	
	@Autowired
	private DeviceRepository devicesRepository;

	public EventProcessorDiscovery() {
		super(new String[]{"Alexa.Discovery"}, new String[]{"Discover"});
	}
	
	@Override
	public Response process(Request request) {
		
		Response response = new Response();
		
		response.event = new Event();
		response.event.header = request.directive.header;
		response.event.header.name = "Discover.Response";
		PayloadDiscoveryResponse payload = new PayloadDiscoveryResponse();
		response.event.payload = payload;
		payload.endpoints = devicesRepository.getEndpoints(); 

		return response;
	}
	
}
