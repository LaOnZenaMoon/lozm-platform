package lozm.chatting.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class WebSocketAPIController {

	private final SimpMessagingTemplate template;


//	@MessageMapping("/chat/getTopicMap")
//	public void getTopicMap(TopicMetadataDto topicMetadataDto) {
//		webSocketMetadata.manageTopicList(topicMetadataDto, 0);
//		if( !assignTopicToConsultant() ) {
//			template.convertAndSend(getStringDecrypt(rabbitMqProperties.getConsultListTopic()), webSocketMetadata.getTopicMap());
//		}
//	}
//
//	@MessageMapping("/chat/subscribe")
//	public void subscribeToTopic(TopicMetadataDto topicMetadataDto) {
//		setSubscribeChatHist(this.setDataMap(topicMetadataDto, ChatHistInfoVo.class), 0);
//
//		webSocketMetadata.manageTopicList(topicMetadataDto, 1);
//		if( !assignTopicToConsultant() ) {
//			template.convertAndSend(
//					getStringDecrypt(rabbitMqProperties.getConsultListTopic()),
//					webSocketMetadata.getTopicMap());
//		}
//	}
//
//	@MessageMapping("/chat/unsubscribe")
//	public void unsubscribeToTopic(TopicMetadataDto topicMetadataDto) {
//		webSocketMetadata.manageTopicList(topicMetadataDto, 2);
//		if( !assignTopicToConsultant() ) {
//			template.convertAndSend(getStringDecrypt(rabbitMqProperties.getConsultListTopic()), webSocketMetadata.getTopicMap());
//		}
//	}
//
//    @MessageMapping("/chat/message")
//    public void sendMessageToTopic(ChatMsgDto chatMsgDto) {
//		ChatMsgDto modifiedDto = null;
//
//		if(chatMsgDto.getClassName().contains("SYS")) {
//			//1. 시스템 메시지 유무 확인
//			modifiedDto = setSystemMessage(chatMsgDto);
//		} else {
//			//2. 필터링 메시지 유무 확인
//			modifiedDto = doFilterMsg(chatMsgDto);
//		}
//
//    	boolean modifyFlag = webSocketMetadata.modifyTopicMap(modifiedDto);
//    	if(modifyFlag) {
//			template.convertAndSend(getStringDecrypt(rabbitMqProperties.getConsultListTopic()), webSocketMetadata.getTopicMap());
//    	}
//
//		template.convertAndSend("/topic/"+chatMsgDto.getTid(), modifiedDto);
//    }

}