import { Scene } from 'phaser';

export default class scene1 extends Scene {
  constructor() {
    super({ key: 'scene1' });
  }

  create() {
    this.chaltteogi = this.physics.add.sprite(200, 200, 'chaltteogi');
    this.chaltteogi.setScale(0.5);
    this.chaltteogi.setCollideWorldBounds(true);
    this.chaltteogi.body.onWorldBounds = true; // enable worldbounds collision event
    this.chaltteogi.setBounce(1);
    this.chaltteogi.setVelocity(200, 20);

    this.text = this.add.text(0, 300, 'Press Any Key to Start');
  }

  update() {
    this.chaltteogi.angle += 1;
    this.text.angle += 1;
  }
}
