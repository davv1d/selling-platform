package com.pik.aggregate;

import com.pik.advertisement.AdvertisementStatus;
import com.pik.advertisement.Photo;
import com.pik.advertisement.command.*;
import com.pik.advertisement.event.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class AdvertisementAggregateTest {
    private FixtureConfiguration<AdvertisementAggregate> fixture;

    @Before
    public void setUp() {
        this.fixture = new AggregateTestFixture<>(AdvertisementAggregate.class);
    }

    @Test
    public void shouldCreateAdvertisement() {
        fixture.givenNoPriorActivity()
                .when(new CreateAdvertisementCommand(
                        "id",
                        "userId",
                        "categoryId",
                        "title",
                        "description",
                        "location",
                        10,
                        new BigDecimal("100"),
                        LocalDateTime.now(),
                        AdvertisementStatus.ACTIVE.toString(),
                        new ArrayList<>()))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AdvertisementCreatedEvent(
                        "id",
                        "userId",
                        "categoryId",
                        "title",
                        "description",
                        "location",
                        10,
                        new BigDecimal("100"),
                        LocalDateTime.now(),
                        AdvertisementStatus.ACTIVE.toString(),
                        new ArrayList<>()));
    }

    @Test
    public void shouldOccurDeadlineAfterSpecifiedTime() {
        fixture.givenNoPriorActivity()
                .when(new CreateAdvertisementCommand(
                        "id",
                        "userId",
                        "categoryId",
                        "title",
                        "description",
                        "location",
                        10,
                        new BigDecimal("100"),
                        LocalDateTime.now(),
                        AdvertisementStatus.ACTIVE.toString(),
                        new ArrayList<>()))
                .expectScheduledDeadlineWithName(Duration.ofDays(10), "ad");
    }


    @Test
    public void shouldNotOccurDeadlineBeforeSpecifiedTime() {
        fixture.givenNoPriorActivity()
                .when(new CreateAdvertisementCommand(
                        "id",
                        "userId",
                        "categoryId",
                        "title",
                        "description",
                        "location",
                        10,
                        new BigDecimal("100"),
                        LocalDateTime.now(),
                        AdvertisementStatus.ACTIVE.toString(),
                        new ArrayList<>()))
                .expectNoScheduledDeadlineWithName(Duration.ofDays(10).minusSeconds(1), "ad");
    }

    @Test
    public void shouldChangeAdvertisement() {
        fixture.given(new AdvertisementCreatedEvent(
                "id",
                "userId",
                "categoryId",
                "title",
                "description",
                "location",
                10,
                new BigDecimal("100"),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                new ArrayList<>()))
                .when(new ChangeAdvertisementCommand(
                        "id",
                        "new title",
                        "new description",
                        BigDecimal.ONE))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AdvertisementChangedEvent("id", "new title", "new description", BigDecimal.ONE));
    }

    @Test
    public void shouldOccurExceptionAdvertisementExpiredAfterChangeAdvertisementCommand() {
        AdvertisementCreatedEvent createEvent = new AdvertisementCreatedEvent(
                "id",
                "userId",
                "categoryId",
                "title",
                "description",
                "location",
                10,
                new BigDecimal("100"),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                new ArrayList<>());
        AdvertisementExpiredEvent advertisementExpiredEvent = new AdvertisementExpiredEvent("id");

        fixture.given(Arrays.asList(createEvent, advertisementExpiredEvent))
                .when(new ChangeAdvertisementCommand("id", "new title", "new description", BigDecimal.ONE))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void shouldAddPhoto() {
        AdvertisementCreatedEvent event = new AdvertisementCreatedEvent(
                "id",
                "userId",
                "categoryId",
                "title",
                "description",
                "location",
                10,
                new BigDecimal("100"),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                new ArrayList<>());
        List<Photo> testPhotoList = Collections.singletonList(new Photo("1", "test1"));
        fixture.given(event)
                .when(new AddAdvertisementPhotosCommand("id", testPhotoList))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AdvertisementPhotoAddedEvent("id", testPhotoList))
                .expectState(ad -> {
                    Assert.assertEquals(1, ad.getPhotoEntities().size());
                });
    }


    @Test
    public void shouldOccurExceptionAdvertisementExpiredAfterAddAdvertisementPhotosCommand() {
        AdvertisementCreatedEvent createEvent = new AdvertisementCreatedEvent(
                "id",
                "userId",
                "categoryId",
                "title",
                "description",
                "location",
                10,
                new BigDecimal("100"),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                new ArrayList<>());
        AdvertisementExpiredEvent advertisementExpiredEvent = new AdvertisementExpiredEvent("id");
        fixture.given(Arrays.asList(createEvent, advertisementExpiredEvent))
                .when(new AddAdvertisementPhotosCommand("id", Collections.singletonList(new Photo("1", "test1"))))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void shouldDeletePhoto() {
        List<String> testPhotosIdsList = Collections.singletonList("photoId_1");
        AdvertisementCreatedEvent event = new AdvertisementCreatedEvent(
                "id",
                "userId",
                "categoryId",
                "title",
                "description",
                "location",
                10,
                new BigDecimal("100"),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                Arrays.asList(new Photo("photoId_1", "test 1"), new Photo("t", "t")));
        fixture.given(event)
                .when(new DeleteAdvertisementPhotoCommand("id", testPhotosIdsList))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AdvertisementPhotoDeletedEvent("id", testPhotosIdsList))
                .expectState(ad -> {
                    Assert.assertEquals(1, ad.getPhotoEntities().size());
                });
    }

    @Test
    public void shouldOccurExceptionAdvertisementExpiredAfterDeleteAdvertisementPhotoCommand() {
        AdvertisementCreatedEvent createEvent = new AdvertisementCreatedEvent(
                "id",
                "userId",
                "categoryId",
                "title",
                "description",
                "location",
                10,
                new BigDecimal("100"),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                new ArrayList<>());
        AdvertisementExpiredEvent advertisementExpiredEvent = new AdvertisementExpiredEvent("id");
        fixture.given(Arrays.asList(createEvent, advertisementExpiredEvent))
                .when(new DeleteAdvertisementPhotoCommand("id", Collections.singletonList("test1")))
                .expectException(IllegalStateException.class);
    }
}
