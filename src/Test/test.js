import { TestWatcher } from "@jest/core";

TestWatcher('fake test',() => {
    expect(true).toBeTruthy();
});