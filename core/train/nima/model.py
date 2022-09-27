import torchvision.models as models
import torch.nn as nn


class Nima(nn.Module):
    def __init__(self, base_model_name="InceptionV3", dropout_rate=0.75) -> None:
        super(Nima, self).__init__()
        self.dropout_rate = dropout_rate
        self.base_model_name = base_model_name
        self.base_module = None
        self._get_base_module()

    def _get_base_module(self) -> None:
        if self.base_model_name == 'InceptionV3':
            self.base_module = models.inception_v3(
                weights=models.Inception_V3_Weights.DEFAULT, dropout=self.dropout_rate)
            self.base_module.aux_logits = False
            self.base_module.fc = nn.Sequential(
                nn.Linear(2048, 10),
                nn.Softmax(dim=-1)
            )
        elif self.base_model_name == "ResNet152":
            self.base_module = models.resnet152(
                weights=models.ResNet152_Weights)
            self.base_module.fc = nn.Sequential(
                nn.Dropout(p=self.dropout_rate),
                nn.Linear(512 * models.resnet.Bottleneck.expansion, 10),
                nn.Softmax(dim=-1)
            )

    def freeze_only_base_module(self) -> None:
        for name, child in self.base_module.named_children():
            if name != "fc":
                child.requires_grad = False

    def unfreeze_all(self) -> None:
        for child in self.base_module.children():
            child.requires_grad = True

    def forward(self, x):
        return self.base_module.forward(x)
