// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

public interface lora_stream_response_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.lora_stream_response_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.helium.poc_lora.lora_stream_session_offer_v1 offer = 1;</code>
   * @return Whether the offer field is set.
   */
  boolean hasOffer();
  /**
   * <code>.helium.poc_lora.lora_stream_session_offer_v1 offer = 1;</code>
   * @return The offer.
   */
  com.helium.grpc.lora_stream_session_offer_v1 getOffer();
  /**
   * <code>.helium.poc_lora.lora_stream_session_offer_v1 offer = 1;</code>
   */
  com.helium.grpc.lora_stream_session_offer_v1OrBuilder getOfferOrBuilder();

  com.helium.grpc.lora_stream_response_v1.ResponseCase getResponseCase();
}
