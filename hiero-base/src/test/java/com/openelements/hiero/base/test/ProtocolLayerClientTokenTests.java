package com.openelements.hiero.base.test;


import com.hedera.hashgraph.sdk.TokenId;
import com.hedera.hashgraph.sdk.TokenType;
import com.openelements.hiero.base.implementation.ProtocolLayerClientImpl;
import com.openelements.hiero.base.protocol.ProtocolLayerClient;
import com.openelements.hiero.base.protocol.TokenBurnRequest;
import com.openelements.hiero.base.protocol.TokenCreateRequest;
import com.openelements.hiero.base.protocol.TokenCreateResult;
import com.openelements.hiero.base.protocol.TokenMintRequest;
import com.openelements.hiero.base.protocol.TokenMintResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProtocolLayerClientTokenTests {

    private static HieroTestContext hederaTestContext;

    private static ProtocolLayerClient protocolLayerClient;

    @BeforeAll
    static void init() {
        hederaTestContext = new HieroTestContext();
        protocolLayerClient = new ProtocolLayerClientImpl(hederaTestContext.getClient(),
                hederaTestContext.getOperationalAccount());
    }

    @Test
    void testBurnNft() throws Exception {
        //given
        final TokenCreateRequest tokenCreateRequest = TokenCreateRequest.of("Test NFT", "TST",
                TokenType.NON_FUNGIBLE_UNIQUE,
                hederaTestContext.getOperationalAccount());
        final TokenCreateResult tokenCreateResult = protocolLayerClient.executeTokenCreateTransaction(
                tokenCreateRequest);
        final TokenId tokenId = tokenCreateResult.tokenId();

        final TokenMintRequest tokenMintRequest = TokenMintRequest.of(tokenId,
                hederaTestContext.getOperationalAccount().privateKey(), "https://example.com/metadata");
        final TokenMintResult tokenMintResult = protocolLayerClient.executeMintTokenTransaction(tokenMintRequest);
        final Long serial = tokenMintResult.serials().get(0);

        //when
        final TokenBurnRequest tokenBurnRequest = TokenBurnRequest.of(tokenId, serial,
                hederaTestContext.getOperationalAccount().privateKey());

        //then
        Assertions.assertDoesNotThrow(() -> protocolLayerClient.executeBurnTokenTransaction(tokenBurnRequest));
    }

}
