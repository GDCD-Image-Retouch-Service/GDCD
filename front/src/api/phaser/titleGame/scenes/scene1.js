import { Scene } from 'phaser';

export default class scene1 extends Scene {
  constructor() {
    super({ key: 'scene1' });
  }

  create() {
    this.chaltteogi = this.physics.add.sprite(200, 0, 'chaltteogi');
    this.chaltteogi.setScale(1);
    this.chaltteogi.setCollideWorldBounds(true);
    this.chaltteogi.body.onWorldBounds = true; // enable worldbounds collision event
    this.chaltteogi.setBounce(1);
    this.chaltteogi.setVelocity(200, 20);
  }

  update() {
    this.chaltteogi.angle += 1;
  }
}
