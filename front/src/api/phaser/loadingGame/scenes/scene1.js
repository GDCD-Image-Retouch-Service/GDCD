import { Scene } from 'phaser';

export default class scene1 extends Scene {
  constructor() {
    super({ key: 'scene1' });
  }

  gameState = {};

  create() {
    this.gameState.notice = this.add.text(150, 300, 'Press Any Key to Start');
    this.gameState.chaltteogi = this.physics.add.sprite(200, 0, 'chaltteogi');
    this.gameState.chaltteogi.setScale(1);
    this.gameState.chaltteogi.setInteractive();
    this.gameState.chaltteogi.setCollideWorldBounds(true);
    this.gameState.chaltteogi.body.onWorldBounds = true; // enable worldbounds collision event
    this.gameState.chaltteogi.setBounce(1);
    this.gameState.chaltteogi.setVelocity(200, 20);
    this.gameState.chaltteogi.on('pointerdown', function () {
      this.setVelocityY(-1000);
    });
    // this.input.keyboard.on('keydown-W', function () {
    //   console.log(this.gameState);
    //   this.gameState.square.fillColor = 0xffff00;
    // });
  }

  update() {
    this.gameState.chaltteogi.angle += 1;
  }
}
